package com.reservastrenque.reservas_trenque.products.usecase;

import com.reservastrenque.reservas_trenque.products.dto.LodgingTypeRequest;
import com.reservastrenque.reservas_trenque.products.model.LodgingType;

import java.util.List;

public interface LodgingTypeUseCase {
    List<LodgingType> getAllActive();
    LodgingType create(LodgingTypeRequest request);
}
