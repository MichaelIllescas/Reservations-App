    package com.reservastrenque.reservas_trenque.products.location.service;

    import com.reservastrenque.reservas_trenque.products.location.dto.CountryResponse;
    import com.reservastrenque.reservas_trenque.products.location.dto.CreateCountryRequest;
    import com.reservastrenque.reservas_trenque.products.location.model.Country;
    import com.reservastrenque.reservas_trenque.products.location.persistence.CountryRepository;
    import com.reservastrenque.reservas_trenque.products.location.usecase.CreateCountryUseCase;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class CreateCountryService implements CreateCountryUseCase {

        private final CountryRepository countryRepository;

        @Override
        public CountryResponse create(CreateCountryRequest request) {
            String name = request.getName().trim().toUpperCase();

            boolean exists = countryRepository.existsByNameIgnoreCase(name);
            if (exists) {
                throw new IllegalArgumentException("Ya existe un país con ese nombre.");
            }

            Country country = Country.builder().name(name).build();
            Country saved = countryRepository.save(country);

            return CountryResponse.builder()
                    .id(saved.getId())
                    .name(saved.getName())
                    .build();
        }

    }
