package com.reservastrenque.reservas_trenque.products.service;

import com.reservastrenque.reservas_trenque.config.exception.EmailAlreadyUsedException;
import com.reservastrenque.reservas_trenque.products.dto.LodgingRequest;
import com.reservastrenque.reservas_trenque.products.dto.LodgingResponse;
import com.reservastrenque.reservas_trenque.products.location.model.City;
import com.reservastrenque.reservas_trenque.products.location.persistence.CityRepository;
import com.reservastrenque.reservas_trenque.products.model.*;
import com.reservastrenque.reservas_trenque.products.persistence.*;
import com.reservastrenque.reservas_trenque.products.usecase.UpdateLodgingUseCase;
import com.reservastrenque.reservas_trenque.products.util.LodgingMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateLodgingService implements UpdateLodgingUseCase {

    private final LodgingRepository lodgingRepository;
    private final LodgingTypeRepository lodgingTypeRepository;
    private final ResponsibleRepository responsibleRepository;
    private final CityRepository cityRepository;
    private final FeatureRepository featureRepository;

    @Value("${upload.base-path}")
    private String baseUploadPath;

    @Override
    public LodgingResponse execute(Long id, LodgingRequest request, MultipartFile[] images) {
        Lodging lodging = lodgingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alojamiento no encontrado con ID: " + id));

        LodgingType lodgingType = lodgingTypeRepository.findById(request.getLodgingTypeId())
                .orElseThrow(() -> new RuntimeException("Tipo de alojamiento no encontrado."));

        City responsibleCity = cityRepository.findById(request.getResponsible().getAddress().getCityId())
                .orElseThrow(() -> new RuntimeException("Ciudad del responsable no encontrada."));

        City lodgingCity = cityRepository.findById(request.getAddress().getCityId())
                .orElseThrow(() -> new RuntimeException("Ciudad del alojamiento no encontrada."));

        lodgingRepository.findByName(request.getName().trim())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(lodging.getId())) {
                        throw new IllegalArgumentException("Ya existe un alojamiento con ese nombre en el sistema");
                    }
                });

        Set<Feature> features = new HashSet<>(featureRepository.findAllById(request.getFeatureIds()));

        Responsible responsible = lodging.getResponsible();
        responsibleRepository.findByEmail(request.getResponsible().getEmail())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(responsible.getId())) {
                        throw new EmailAlreadyUsedException(request.getResponsible().getEmail());
                    }
                });

        Address respAddress = responsible.getAddress();
        respAddress.setStreet(request.getResponsible().getAddress().getStreet());
        respAddress.setNumber(request.getResponsible().getAddress().getNumber());
        respAddress.setCity(responsibleCity);

        responsible.setFullName(request.getResponsible().getFullName());
        responsible.setEmail(request.getResponsible().getEmail());
        responsible.setPhone(request.getResponsible().getPhone());
        responsible.setDocumentNumber(request.getResponsible().getDocumentNumber());
        responsible.setAddress(respAddress);

        Address lodAddress = lodging.getAddress();
        lodAddress.setStreet(request.getAddress().getStreet());
        lodAddress.setNumber(request.getAddress().getNumber());
        lodAddress.setCity(lodgingCity);

        lodging.setName(request.getName());
        lodging.setDescription(request.getDescription());
        lodging.setDailyPrice(request.getDailyPrice());
        lodging.setCapacity(request.getCapacity());
        lodging.setType(lodgingType);
        lodging.setFeatures(features);
        lodging.setAddress(lodAddress);
        lodging.setResponsible(responsible);

        if (images != null && images.length > 0) {
            deleteLodgingImageFolder(lodging.getId());
            String folderName = "alojamiento" + lodging.getId();
            String fullFolderPath = baseUploadPath + File.separator + folderName;
            new File(fullFolderPath).mkdirs();

            List<String> imageUrls = new ArrayList<>();
            for (MultipartFile image : images) {
                String originalFilename = image.getOriginalFilename();
                if (originalFilename == null || !originalFilename.matches("(?i)^.*\\.(jpg|jpeg|png|webp|gif)$")) {
                    throw new IllegalArgumentException("Extensión de archivo no válida. Solo se permiten JPG, PNG, GIF, WEBP.");
                }
                String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;
                Path path = Paths.get(fullFolderPath, uniqueFilename);
                try {
                    Files.write(path, image.getBytes());
                    imageUrls.add("/images/" + folderName + "/" + uniqueFilename);
                } catch (IOException e) {
                    throw new RuntimeException("Error al guardar imagen: " + uniqueFilename, e);
                }
            }
            lodging.setImageUrls(imageUrls);
        }

        Lodging saved = lodgingRepository.save(lodging);
        return LodgingMapper.toResponse(saved);
    }

    private void deleteLodgingImageFolder(Long lodgingId) {
        Path lodgingFolderPath = Paths.get(baseUploadPath, "alojamiento" + lodgingId).toAbsolutePath();
        if (Files.exists(lodgingFolderPath)) {
            try {
                Files.walk(lodgingFolderPath)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (IOException e) {
                throw new RuntimeException("Error al eliminar la carpeta de imágenes del alojamiento: " + lodgingFolderPath, e);
            }
        }
    }
}

