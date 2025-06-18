package com.reservastrenque.reservas_trenque.products.location.service;

import com.reservastrenque.reservas_trenque.products.location.dto.CreateProvinceRequest;
import com.reservastrenque.reservas_trenque.products.location.dto.ProvinceResponse;
import com.reservastrenque.reservas_trenque.products.location.model.Country;
import com.reservastrenque.reservas_trenque.products.location.model.Province;
import com.reservastrenque.reservas_trenque.products.location.persistence.CountryRepository;
import com.reservastrenque.reservas_trenque.products.location.persistence.ProvinceRepository;
import com.reservastrenque.reservas_trenque.products.location.usecase.CreateProvinceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProvinceService implements CreateProvinceUseCase {

    private final CountryRepository countryRepository;
    private final ProvinceRepository provinceRepository;

    @Override
    public ProvinceResponse create(CreateProvinceRequest request) {
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("País no encontrado"));

        String name = request.getName().trim();

        boolean exists = provinceRepository.existsByNameIgnoreCaseAndCountryId(name, country.getId());
        if (exists) {
            throw new IllegalArgumentException("Ya existe una provincia con ese nombre en ese país.");
        }

        Province province = Province.builder()
                .name(name)
                .country(country)
                .build();

        Province saved = provinceRepository.save(province);

        return ProvinceResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .countryId(country.getId())
                .build();
    }

}
