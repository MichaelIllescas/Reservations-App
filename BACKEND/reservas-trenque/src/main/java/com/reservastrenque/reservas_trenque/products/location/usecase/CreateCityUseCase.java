package com.reservastrenque.reservas_trenque.products.location.usecase;

import com.reservastrenque.reservas_trenque.products.location.dto.CreateCityRequest;
import com.reservastrenque.reservas_trenque.products.location.dto.CityResponse;

public interface CreateCityUseCase {
    CityResponse create(CreateCityRequest request);
}
