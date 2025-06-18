package com.reservastrenque.reservas_trenque.products.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibleRequest {
    private String fullName;
    private String email;
    private String phone;
    private String documentNumber;

    private AddressRequest address;
}
