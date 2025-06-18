package com.reservastrenque.reservas_trenque.products.location.controller;

import com.reservastrenque.reservas_trenque.products.location.dto.CityResponse;
import com.reservastrenque.reservas_trenque.products.location.dto.CreateCityRequest;
import com.reservastrenque.reservas_trenque.products.location.usecase.CreateCityUseCase;
import com.reservastrenque.reservas_trenque.products.location.usecase.GetCitiesByProvinceUseCase;
import com.reservastrenque.reservas_trenque.shared.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

    private final CreateCityUseCase createCityUseCase;
    private final GetCitiesByProvinceUseCase getCitiesByProvinceUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<CityResponse>> create(@Valid @RequestBody CreateCityRequest request) {
        CityResponse response = createCityUseCase.create(request);
        return ResponseEntity.ok(new ApiResponse<>("Ciudad creada exitosamente", response));
    }

    @GetMapping("/by-province/{provinceId}")
    public ResponseEntity<ApiResponse<List<CityResponse>>> getByProvince(@PathVariable Long provinceId) {
        List<CityResponse> cities = getCitiesByProvinceUseCase.findByProvinceId(provinceId);
        return ResponseEntity.ok(new ApiResponse<>("Lista de ciudades", cities));
    }
}
