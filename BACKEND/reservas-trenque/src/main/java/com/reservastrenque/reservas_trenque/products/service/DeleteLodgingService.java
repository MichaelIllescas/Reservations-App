package com.reservastrenque.reservas_trenque.products.service;

import com.reservastrenque.reservas_trenque.products.persistence.LodgingRepository;
import com.reservastrenque.reservas_trenque.products.usecase.DeleteLodgingUseCase;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
@Service
@RequiredArgsConstructor
@Transactional
public class DeleteLodgingService implements DeleteLodgingUseCase {

    private final LodgingRepository lodgingRepository;

    @Value("${upload.base-path}")
    private String baseUploadPath;

    @Override
    public void deleteById(Long id) {
        var lodging = lodgingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alojamiento no encontrado con ID: " + id));

        deleteLodgingImageFolder(id);
        lodgingRepository.delete(lodging);
    }

    private void deleteLodgingImageFolder(Long lodgingId) {
        Path lodgingFolderPath = Paths.get(baseUploadPath, "alojamiento" + lodgingId).toAbsolutePath();

        if (Files.exists(lodgingFolderPath)) {
            try {
                Files.walk(lodgingFolderPath)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(file -> {
                            boolean deleted = file.delete();
                            if (!deleted) {
                                System.err.println("❌ No se pudo eliminar: " + file.getAbsolutePath());
                            }
                        });
            } catch (IOException e) {
                throw new RuntimeException("Error al eliminar la carpeta de imágenes del alojamiento: " + lodgingFolderPath, e);
            }
        } else {
            System.err.println("⚠️ La carpeta no existe: " + lodgingFolderPath);
        }
    }
}