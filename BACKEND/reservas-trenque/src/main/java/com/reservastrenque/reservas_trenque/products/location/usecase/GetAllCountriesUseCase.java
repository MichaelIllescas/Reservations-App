package com.reservastrenque.reservas_trenque.products.location.usecase;

import com.reservastrenque.reservas_trenque.products.location.dto.CountryResponse;

import java.util.List;

public interface GetAllCountriesUseCase {
    List<CountryResponse> findAll();
}
