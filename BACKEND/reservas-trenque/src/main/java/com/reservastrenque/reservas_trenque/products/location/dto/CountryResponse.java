package com.reservastrenque.reservas_trenque.products.location.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryResponse {
    private Long id;
    private String name;
}
