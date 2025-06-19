package com.reservastrenque.reservas_trenque.products.util;

import com.reservastrenque.reservas_trenque.products.dto.LodgingTypeResponse;
import com.reservastrenque.reservas_trenque.products.model.LodgingType;

public class LodgingTypeMapper {

    public static LodgingTypeResponse toDto(LodgingType entity) {
        return LodgingTypeResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .icon(entity.getIcon())
                .build();
    }
}
