package com.reservastrenque.reservas_trenque.products.service;

import com.reservastrenque.reservas_trenque.config.exception.EmailAlreadyUsedException;
import com.reservastrenque.reservas_trenque.products.dto.LodgingRequest;
import com.reservastrenque.reservas_trenque.products.dto.LodgingResponse;
import com.reservastrenque.reservas_trenque.products.location.model.City;
import com.reservastrenque.reservas_trenque.products.location.persistence.CityRepository;
import com.reservastrenque.reservas_trenque.products.model.*;
import com.reservastrenque.reservas_trenque.products.persistence.*;
import com.reservastrenque.reservas_trenque.products.usecase.CreateLodgingUseCase;
import com.reservastrenque.reservas_trenque.products.util.LodgingMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateLodgingService implements CreateLodgingUseCase {

    private final LodgingRepository lodgingRepository;
    private final LodgingTypeRepository lodgingTypeRepository;
    private final ResponsibleRepository responsibleRepository;
    private final CityRepository cityRepository;
    private final FeatureRepository featureRepository;

    @Value("${upload.base-path}")
    private String baseUploadPath;

    @Override
    public LodgingResponse execute(LodgingRequest request, MultipartFile[] images) {

        // Validar y obtener tipo de alojamiento
        LodgingType lodgingType = lodgingTypeRepository.findById(request.getLodgingTypeId())
                .orElseThrow(() -> new RuntimeException("Tipo de alojamiento no encontrado."));

        // Validar y obtener ciudad del responsable
        City responsibleCity = cityRepository.findById(
                request.getResponsible().getAddress().getCityId()
        ).orElseThrow(() -> new RuntimeException("Ciudad del responsable no encontrada."));

        // Crear dirección del responsable
        Address responsibleAddress = Address.builder()
                .street(request.getResponsible().getAddress().getStreet())
                .number(request.getResponsible().getAddress().getNumber())
                .city(responsibleCity)
                .build();

        //validar existencia de ese responsable en la bd
        if (responsibleRepository.findByEmail(request.getResponsible().getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException(request.getResponsible().getEmail());
        }
        // Crear y guardar responsable
            Responsible responsible = Responsible.builder()
                .fullName(request.getResponsible().getFullName())
                .email(request.getResponsible().getEmail())
                .phone(request.getResponsible().getPhone())
                .documentNumber(request.getResponsible().getDocumentNumber())
                .address(responsibleAddress)
                .build();

        responsibleRepository.save(responsible);

        // Validar y obtener ciudad del alojamiento
        City lodgingCity = cityRepository.findById(request.getAddress().getCityId())
                .orElseThrow(() -> new RuntimeException("Ciudad del alojamiento no encontrada."));

        // Crear dirección del alojamiento
        Address lodgingAddress = Address.builder()
                .street(request.getAddress().getStreet())
                .number(request.getAddress().getNumber())
                .city(lodgingCity)
                .build();

        //Validar que no exista un alojamiento con el mismo nombre

        lodgingRepository.findByName(request.getName().trim())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Ya existe un alojamiento con ese nombre en el sistema");
                });


        // Obtener características seleccionadas
        Set<Feature> features = new HashSet<>(featureRepository.findAllById(request.getFeatureIds()));

        // Crear entidad alojamiento sin imágenes aún
        Lodging lodging = Lodging.builder()
                .name(request.getName())
                .description(request.getDescription())
                .dailyPrice(request.getDailyPrice())
                .capacity(request.getCapacity())
                .type(lodgingType)
                .features(features)
                .address(lodgingAddress)
                .responsible(responsible)
                .build();

        Lodging savedLodging = lodgingRepository.save(lodging);

        // Validar imágenes
        if (images == null || images.length == 0) {
            throw new IllegalArgumentException("Debe enviar al menos una imagen.");
        }

        // Crear carpeta de imágenes
        String folderName = "alojamiento" + savedLodging.getId();
        String fullFolderPath = baseUploadPath + File.separator + folderName;
        new File(fullFolderPath).mkdirs();

        // Guardar imágenes y generar URLs
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

        // Asociar imágenes y guardar alojamiento
        savedLodging.setImageUrls(imageUrls);
        savedLodging = lodgingRepository.save(savedLodging);

        return LodgingMapper.toResponse(savedLodging);
    }

}
