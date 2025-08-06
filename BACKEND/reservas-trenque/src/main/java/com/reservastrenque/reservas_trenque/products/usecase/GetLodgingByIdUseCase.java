package com.reservastrenque.reservas_trenque.products.usecase;

import com.reservastrenque.reservas_trenque.products.dto.LodgingDetailResponse;

public interface GetLodgingByIdUseCase {
    LodgingDetailResponse execute(Long id);
}

