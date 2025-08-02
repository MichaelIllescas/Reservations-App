package com.reservastrenque.reservas_trenque.products.controller;

import com.reservastrenque.reservas_trenque.products.dto.FeatureRequest;
import com.reservastrenque.reservas_trenque.products.dto.FeatureResponse;
import com.reservastrenque.reservas_trenque.products.service.FeatureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/features")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureService featureService;

    @GetMapping
    public ResponseEntity<List<FeatureResponse>> getAllFeatures() {
        return ResponseEntity.ok(featureService.getAll());
    }

    @PostMapping
    public ResponseEntity<FeatureResponse> createFeature(@Valid @RequestBody FeatureRequest request) {
        return ResponseEntity.ok(featureService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeatureResponse> updateFeature(@PathVariable Long id,
                                                         @Valid @RequestBody FeatureRequest request) {
        return ResponseEntity.ok(featureService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable Long id) {
        featureService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
