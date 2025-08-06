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
public class ResponsibleDetailResponse {
    private String fullName;
    private String email;
    private String phone;
    private String documentNumber;
    private AddressDetailResponse address;
}

