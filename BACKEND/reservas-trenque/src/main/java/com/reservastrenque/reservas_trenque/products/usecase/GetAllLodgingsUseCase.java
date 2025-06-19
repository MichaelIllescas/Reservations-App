package com.reservastrenque.reservas_trenque.products.usecase;

import com.reservastrenque.reservas_trenque.products.dto.LodgingResponse;
import java.util.List;

public interface GetAllLodgingsUseCase {
    List<LodgingResponse> execute();
}
