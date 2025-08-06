package com.reservastrenque.reservas_trenque.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDetailResponse {
    private String street;
    private String number;
    private Long cityId;
    private Long provinceId;
    private Long countryId;
}

