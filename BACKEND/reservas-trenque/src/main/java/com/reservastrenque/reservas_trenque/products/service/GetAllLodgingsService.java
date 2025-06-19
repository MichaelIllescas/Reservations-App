package com.reservastrenque.reservas_trenque.products.service;

import com.reservastrenque.reservas_trenque.products.dto.LodgingResponse;

import com.reservastrenque.reservas_trenque.products.persistence.LodgingRepository;
import com.reservastrenque.reservas_trenque.products.usecase.GetAllLodgingsUseCase;
import com.reservastrenque.reservas_trenque.products.util.LodgingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllLodgingsService implements GetAllLodgingsUseCase {

    private final LodgingRepository lodgingRepository;
    private final LodgingMapper lodgingMapper;

    @Override
    public List<LodgingResponse> execute() {
        return lodgingRepository.findAll()
                .stream()
                .map(e->LodgingMapper.toResponse(e))
                .collect(Collectors.toList());
    }
}
