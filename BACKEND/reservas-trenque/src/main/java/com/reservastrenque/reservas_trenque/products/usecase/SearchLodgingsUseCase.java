package com.reservastrenque.reservas_trenque.products.usecase;

import com.reservastrenque.reservas_trenque.products.dto.LodgingResponse;

import java.time.LocalDate;
import java.util.List;

public interface SearchLodgingsUseCase {
    List<LodgingResponse> execute(String query, LocalDate startDate, LocalDate endDate);
}
