package com.reservastrenque.reservas_trenque.products.location.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProvinceRequest {
    @NotBlank(message = "El nombre de la provincia es obligatorio.")
    private String name;

    @NotNull(message = "Debe asociarse a un país.")
    private Long countryId;
}
