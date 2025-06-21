package com.reservastrenque.reservas_trenque.products.service;

import com.reservastrenque.reservas_trenque.products.dto.LodgingResponse;

import com.reservastrenque.reservas_trenque.products.persistence.LodgingRepository;
import com.reservastrenque.reservas_trenque.products.usecase.GetAllLodgingsUseCase;
import com.reservastrenque.reservas_trenque.products.util.LodgingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllLodgingsService implements GetAllLodgingsUseCase {

    private final LodgingRepository lodgingRepository;
    private final LodgingMapper lodgingMapper;

    @Override
    public List<LodgingResponse> execute() {
        List<LodgingResponse> responseList = lodgingRepository.findAll()
                .stream()
                .map(LodgingMapper::toResponse)
                .collect(Collectors.toList());

        Collections.shuffle(responseList);

        return responseList;
    }
}
