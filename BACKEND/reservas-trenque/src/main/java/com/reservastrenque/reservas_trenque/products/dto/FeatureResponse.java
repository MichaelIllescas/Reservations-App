package com.reservastrenque.reservas_trenque.products.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureResponse {
    private Long id;
    private String name;
    private String icon;
}

