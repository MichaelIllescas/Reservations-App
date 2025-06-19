// src/main/java/com/reservastrenque/reservas_trenque/products/controller/FeatureController.java
package com.reservastrenque.reservas_trenque.products.controller;

import com.reservastrenque.reservas_trenque.products.model.LodgingFeature;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/features")
@CrossOrigin(origins = "*") // o configurado con CORS global
public class FeatureController {

    @GetMapping
    public ResponseEntity<List<String>> getAllFeatures() {
        List<String> features = Arrays.stream(LodgingFeature.values())
                .map(Enum::name)
                .toList();
        return ResponseEntity.ok(features);
    }
}
