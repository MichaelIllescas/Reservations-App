package com.reservastrenque.reservas_trenque.products.location.service;

import com.reservastrenque.reservas_trenque.products.location.dto.CountryResponse;
import com.reservastrenque.reservas_trenque.products.location.model.Country;
import com.reservastrenque.reservas_trenque.products.location.persistence.CountryRepository;
import com.reservastrenque.reservas_trenque.products.location.usecase.GetAllCountriesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCountriesService implements GetAllCountriesUseCase {

    private final CountryRepository countryRepository;

    @Override
    public List<CountryResponse> findAll() {
        return countryRepository.findAll()
                .stream()
                .map(c -> CountryResponse.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .build())
                .toList();
    }
}
