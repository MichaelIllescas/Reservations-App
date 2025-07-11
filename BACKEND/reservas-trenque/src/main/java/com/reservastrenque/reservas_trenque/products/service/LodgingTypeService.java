package com.reservastrenque.reservas_trenque.products.service;

import com.reservastrenque.reservas_trenque.products.dto.LodgingTypeRequest;
import com.reservastrenque.reservas_trenque.products.model.LodgingType;
import com.reservastrenque.reservas_trenque.products.persistence.LodgingTypeRepository;
import com.reservastrenque.reservas_trenque.products.usecase.LodgingTypeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LodgingTypeService implements LodgingTypeUseCase {

    private final LodgingTypeRepository repository;

    @Override
    public List<LodgingType> getAllActive() {
        return repository.findAll().stream()
                .filter(t -> Boolean.TRUE.equals(t.getActive()))
                .toList();
    }

    @Override
    public LodgingType create(LodgingTypeRequest request) {
        LodgingType type = LodgingType.builder()
                .name(request.getName())
                .icon(request.getIcon())
                .active(true)
                .build();

        return repository.save(type);
    }
}
