package com.reservastrenque.reservas_trenque.products.location.service;

import com.reservastrenque.reservas_trenque.products.location.dto.ProvinceResponse;
import com.reservastrenque.reservas_trenque.products.location.persistence.ProvinceRepository;
import com.reservastrenque.reservas_trenque.products.location.usecase.GetProvincesByCountryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProvincesByCountryService implements GetProvincesByCountryUseCase {

    private final ProvinceRepository provinceRepository;

    @Override
    public List<ProvinceResponse> findByCountryId(Long countryId) {
        return provinceRepository.findByCountryId(countryId)
                .stream()
                .map(p -> ProvinceResponse.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .countryId(p.getCountry().getId())
                        .build())
                .toList();
    }
}
