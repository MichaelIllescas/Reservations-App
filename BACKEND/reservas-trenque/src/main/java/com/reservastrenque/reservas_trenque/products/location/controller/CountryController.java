package com.reservastrenque.reservas_trenque.products.location.controller;

import com.reservastrenque.reservas_trenque.products.location.dto.CountryResponse;
import com.reservastrenque.reservas_trenque.products.location.dto.CreateCountryRequest;
import com.reservastrenque.reservas_trenque.products.location.usecase.CreateCountryUseCase;
import com.reservastrenque.reservas_trenque.products.location.usecase.GetAllCountriesUseCase;
import com.reservastrenque.reservas_trenque.shared.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CreateCountryUseCase createCountryUseCase;
    private final GetAllCountriesUseCase getAllCountriesUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<CountryResponse>> create(@Valid @RequestBody CreateCountryRequest request) {
        CountryResponse response = createCountryUseCase.create(request);
        return ResponseEntity.ok(new ApiResponse<>("País creado exitosamente", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CountryResponse>>> getAll() {
        List<CountryResponse> countries = getAllCountriesUseCase.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Lista de países", countries));
    }
}
