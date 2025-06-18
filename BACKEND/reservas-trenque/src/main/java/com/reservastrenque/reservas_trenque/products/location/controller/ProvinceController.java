package com.reservastrenque.reservas_trenque.products.location.controller;

import com.reservastrenque.reservas_trenque.products.location.dto.CreateProvinceRequest;
import com.reservastrenque.reservas_trenque.products.location.dto.ProvinceResponse;
import com.reservastrenque.reservas_trenque.products.location.usecase.CreateProvinceUseCase;
import com.reservastrenque.reservas_trenque.products.location.usecase.GetProvincesByCountryUseCase;
import com.reservastrenque.reservas_trenque.shared.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provinces")
@RequiredArgsConstructor
public class ProvinceController {

    private final CreateProvinceUseCase createProvinceUseCase;
    private final GetProvincesByCountryUseCase getProvincesByCountryUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<ProvinceResponse>> create(@Valid @RequestBody CreateProvinceRequest request) {
        ProvinceResponse response = createProvinceUseCase.create(request);
        return ResponseEntity.ok(new ApiResponse<>("Provincia creada exitosamente", response));
    }

    @GetMapping("/by-country/{countryId}")
    public ResponseEntity<ApiResponse<List<ProvinceResponse>>> getByCountry(@PathVariable Long countryId) {
        List<ProvinceResponse> provinces = getProvincesByCountryUseCase.findByCountryId(countryId);
        return ResponseEntity.ok(new ApiResponse<>("Lista de provincias", provinces));
    }
}
