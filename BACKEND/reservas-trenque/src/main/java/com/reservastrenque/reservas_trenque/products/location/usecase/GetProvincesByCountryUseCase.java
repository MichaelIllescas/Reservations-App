package com.reservastrenque.reservas_trenque.products.location.usecase;

import com.reservastrenque.reservas_trenque.products.location.dto.ProvinceResponse;

import java.util.List;

public interface GetProvincesByCountryUseCase {
    List<ProvinceResponse> findByCountryId(Long countryId);
}
