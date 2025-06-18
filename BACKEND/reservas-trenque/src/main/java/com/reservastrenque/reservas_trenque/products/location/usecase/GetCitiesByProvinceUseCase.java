package com.reservastrenque.reservas_trenque.products.location.usecase;

import com.reservastrenque.reservas_trenque.products.location.dto.CityResponse;

import java.util.List;

public interface GetCitiesByProvinceUseCase {
    List<CityResponse> findByProvinceId(Long provinceId);
}
