package com.reservastrenque.reservas_trenque.products.location.service;

import com.reservastrenque.reservas_trenque.products.location.dto.CityResponse;
import com.reservastrenque.reservas_trenque.products.location.dto.CreateCityRequest;
import com.reservastrenque.reservas_trenque.products.location.model.City;
import com.reservastrenque.reservas_trenque.products.location.model.Province;
import com.reservastrenque.reservas_trenque.products.location.persistence.CityRepository;
import com.reservastrenque.reservas_trenque.products.location.persistence.ProvinceRepository;
import com.reservastrenque.reservas_trenque.products.location.usecase.CreateCityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCityService implements CreateCityUseCase {

    private final ProvinceRepository provinceRepository;
    private final CityRepository cityRepository;

    @Override
    public CityResponse create(CreateCityRequest request) {
        Province province = provinceRepository.findById(request.getProvinceId())
                .orElseThrow(() -> new IllegalArgumentException("Provincia no encontrada"));

        String name = request.getName().trim();

        boolean exists = cityRepository.existsByNameIgnoreCaseAndProvinceId(name, province.getId());
        if (exists) {
            throw new IllegalArgumentException("Ya existe una ciudad con ese nombre en esa provincia.");
        }

        City city = City.builder()
                .name(name)
                .province(province)
                .build();

        City saved = cityRepository.save(city);

        return CityResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .provinceId(province.getId())
                .build();
    }

}
