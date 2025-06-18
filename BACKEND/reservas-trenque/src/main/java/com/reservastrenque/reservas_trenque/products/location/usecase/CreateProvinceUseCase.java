package com.reservastrenque.reservas_trenque.products.location.usecase;

import com.reservastrenque.reservas_trenque.products.location.dto.CreateProvinceRequest;
import com.reservastrenque.reservas_trenque.products.location.dto.ProvinceResponse;

public interface CreateProvinceUseCase {
    ProvinceResponse create(CreateProvinceRequest request);
}
