package com.reservastrenque.reservas_trenque.products.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponse {

    private String street;
    private String number;
    private String city;
    private String province;
    private String country;
}
