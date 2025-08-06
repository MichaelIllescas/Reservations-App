package com.reservastrenque.reservas_trenque.products.service;

import com.reservastrenque.reservas_trenque.products.dto.LodgingDetailResponse;
import com.reservastrenque.reservas_trenque.products.persistence.LodgingRepository;
import com.reservastrenque.reservas_trenque.products.usecase.GetLodgingByIdUseCase;
import com.reservastrenque.reservas_trenque.products.util.LodgingMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLodgingByIdService implements GetLodgingByIdUseCase {

    private final LodgingRepository lodgingRepository;

    @Override
    public LodgingDetailResponse execute(Long id) {
        var lodging = lodgingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alojamiento no encontrado con ID: " + id));
        return LodgingMapper.toDetailResponse(lodging);
    }
}

