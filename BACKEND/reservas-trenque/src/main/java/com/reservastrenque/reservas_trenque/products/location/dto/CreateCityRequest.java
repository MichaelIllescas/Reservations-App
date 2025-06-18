package com.reservastrenque.reservas_trenque.products.location.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCityRequest {
    @NotBlank(message = "El nombre de la ciudad es obligatorio.")
    private String name;

    @NotNull(message = "Debe asociarse a una provincia.")
    private Long provinceId;
}
