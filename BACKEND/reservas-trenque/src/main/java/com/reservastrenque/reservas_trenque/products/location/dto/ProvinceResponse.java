package com.reservastrenque.reservas_trenque.products.location.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProvinceResponse {
    private Long id;
    private String name;
    private Long countryId;
}
