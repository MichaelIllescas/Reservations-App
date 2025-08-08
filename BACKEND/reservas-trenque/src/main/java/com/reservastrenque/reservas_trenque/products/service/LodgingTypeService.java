package com.reservastrenque.reservas_trenque.products.service;

import com.reservastrenque.reservas_trenque.products.dto.LodgingTypeRequest;
import com.reservastrenque.reservas_trenque.products.model.LodgingType;
import com.reservastrenque.reservas_trenque.products.persistence.LodgingTypeRepository;
import com.reservastrenque.reservas_trenque.products.usecase.LodgingTypeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public LodgingType update(Long id, LodgingTypeRequest request) {
        LodgingType type = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tipo de alojamiento no encontrado"));
        type.setName(request.getName());
        type.setIcon(request.getIcon());
        return repository.save(type);
    }

    @Override
    public void delete(Long id) {
        LodgingType type = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tipo de alojamiento no encontrado"));
        type.setActive(false);
        repository.save(type);
    }
}
