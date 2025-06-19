package com.reservastrenque.reservas_trenque.products.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LodgingTypeResponse {
    private Long id;
    private String name;
    private String icon;
}
