package com.reservastrenque.reservas_trenque.products.usecase;

import com.reservastrenque.reservas_trenque.products.dto.LodgingRequest;
import com.reservastrenque.reservas_trenque.products.dto.LodgingResponse;
import com.reservastrenque.reservas_trenque.products.model.Lodging;
import org.springframework.web.multipart.MultipartFile;


public interface CreateLodgingUseCase {
    LodgingResponse execute(LodgingRequest request, MultipartFile[] images);
}