package com.reservastrenque.reservas_trenque.products.location.usecase;

import com.reservastrenque.reservas_trenque.products.location.dto.CreateCountryRequest;
import com.reservastrenque.reservas_trenque.products.location.dto.CountryResponse;

public interface CreateCountryUseCase {
    CountryResponse create(CreateCountryRequest request);
}
