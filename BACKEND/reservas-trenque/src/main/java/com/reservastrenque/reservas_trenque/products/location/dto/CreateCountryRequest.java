package com.reservastrenque.reservas_trenque.products.location.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCountryRequest {
    @NotBlank(message = "El nombre del país es obligatorio.")
    private String name;
}
