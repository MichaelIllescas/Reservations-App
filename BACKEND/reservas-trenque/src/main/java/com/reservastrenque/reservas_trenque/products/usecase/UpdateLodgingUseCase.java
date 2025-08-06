package com.reservastrenque.reservas_trenque.products.usecase;

import com.reservastrenque.reservas_trenque.products.dto.LodgingRequest;
import com.reservastrenque.reservas_trenque.products.dto.LodgingResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateLodgingUseCase {
    LodgingResponse execute(Long id, LodgingRequest request, MultipartFile[] images);
}

