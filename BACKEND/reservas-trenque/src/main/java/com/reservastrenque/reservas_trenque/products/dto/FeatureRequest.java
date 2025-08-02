package com.reservastrenque.reservas_trenque.products.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureRequest {

    @NotBlank(message = "El nombre es obligatorio.")
    private String name;

    private String icon;
}

