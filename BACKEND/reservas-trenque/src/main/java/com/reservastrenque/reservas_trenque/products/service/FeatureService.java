package com.reservastrenque.reservas_trenque.products.service;

import com.reservastrenque.reservas_trenque.products.dto.FeatureRequest;
import com.reservastrenque.reservas_trenque.products.dto.FeatureResponse;
import com.reservastrenque.reservas_trenque.products.model.Feature;
import com.reservastrenque.reservas_trenque.products.persistence.FeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureRepository featureRepository;

    public List<FeatureResponse> getAll() {
        return featureRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public FeatureResponse create(FeatureRequest request) {
        Feature feature = Feature.builder()
                .name(request.getName())
                .icon(request.getIcon())
                .build();
        return toResponse(featureRepository.save(feature));
    }

    public FeatureResponse update(Long id, FeatureRequest request) {
        Feature feature = featureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Característica no encontrada"));
        feature.setName(request.getName());
        feature.setIcon(request.getIcon());
        return toResponse(featureRepository.save(feature));
    }

    public void delete(Long id) {
        featureRepository.deleteById(id);
    }

    private FeatureResponse toResponse(Feature feature) {
        return FeatureResponse.builder()
                .id(feature.getId())
                .name(feature.getName())
                .icon(feature.getIcon())
                .build();
    }
}

