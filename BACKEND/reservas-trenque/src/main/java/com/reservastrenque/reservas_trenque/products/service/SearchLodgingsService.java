package com.reservastrenque.reservas_trenque.products.service;

import com.reservastrenque.reservas_trenque.products.dto.LodgingResponse;
import com.reservastrenque.reservas_trenque.products.persistence.LodgingRepository;
import com.reservastrenque.reservas_trenque.products.usecase.SearchLodgingsUseCase;
import com.reservastrenque.reservas_trenque.products.util.LodgingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchLodgingsService implements SearchLodgingsUseCase {

    private final LodgingRepository lodgingRepository;

    @Transactional(readOnly = true)
    @Override
    public List<LodgingResponse> execute(String query, LocalDate startDate, LocalDate endDate) {
        if (query == null || query.isBlank()) {
            return List.of();
        }
        return lodgingRepository.searchByQuery(query).stream()
                .map(LodgingMapper::toResponse)
                .collect(Collectors.toList());
    }
}
