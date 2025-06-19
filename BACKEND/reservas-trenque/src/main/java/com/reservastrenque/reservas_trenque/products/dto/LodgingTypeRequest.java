package com.reservastrenque.reservas_trenque.products.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LodgingTypeRequest {

    @NotBlank(message = "El nombre es obligatorio.")
    private String name;

    private String icon;
}
