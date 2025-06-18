package com.reservastrenque.reservas_trenque.products.location.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityResponse {
    private Long id;
    private String name;
    private Long provinceId;
}
