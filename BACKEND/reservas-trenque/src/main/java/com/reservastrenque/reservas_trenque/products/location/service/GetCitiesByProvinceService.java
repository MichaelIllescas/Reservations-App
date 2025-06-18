package com.reservastrenque.reservas_trenque.products.location.service;

import com.reservastrenque.reservas_trenque.products.location.dto.CityResponse;
import com.reservastrenque.reservas_trenque.products.location.persistence.CityRepository;
import com.reservastrenque.reservas_trenque.products.location.usecase.GetCitiesByProvinceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCitiesByProvinceService implements GetCitiesByProvinceUseCase {

    private final CityRepository cityRepository;

    @Override
    public List<CityResponse> findByProvinceId(Long provinceId) {
        return cityRepository.findByProvinceId(provinceId)
                .stream()
                .map(c -> CityResponse.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .provinceId(c.getProvince().getId())
                        .build())
                .toList();
    }
}
