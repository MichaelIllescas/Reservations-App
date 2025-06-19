package com.reservastrenque.reservas_trenque.products.controller;

import com.reservastrenque.reservas_trenque.products.dto.LodgingTypeRequest;
import com.reservastrenque.reservas_trenque.products.dto.LodgingTypeResponse;
import com.reservastrenque.reservas_trenque.products.model.LodgingType;
import com.reservastrenque.reservas_trenque.products.usecase.LodgingTypeUseCase;
import com.reservastrenque.reservas_trenque.products.util.LodgingTypeMapper;
import com.reservastrenque.reservas_trenque.shared.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lodging-types")
public class LodgingTypeController {

    private final LodgingTypeUseCase lodgingTypeUseCase;

    @GetMapping
    public ResponseEntity<ApiResponse<List<LodgingTypeResponse>>> getAll() {
        List<LodgingTypeResponse> list = lodgingTypeUseCase.getAllActive()
                .stream()
                .map(e->LodgingTypeMapper.toDto(e))
                .toList();

        return ResponseEntity.ok(new ApiResponse<>("Tipos de alojamiento activos", list));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LodgingTypeResponse>> create(@Valid @RequestBody LodgingTypeRequest request) {
        LodgingType created = lodgingTypeUseCase.create(request);
        return ResponseEntity.ok(new ApiResponse<>("Tipo creado", LodgingTypeMapper.toDto(created)));
    }
}
