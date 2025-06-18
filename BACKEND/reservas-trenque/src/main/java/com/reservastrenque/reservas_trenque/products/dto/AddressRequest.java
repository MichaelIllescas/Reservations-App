package com.reservastrenque.reservas_trenque.products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRequest {

    @NotBlank(message = "La calle no puede estar vacía.")
    private String street;

    @NotBlank(message = "El número no puede estar vacío.")
    private String number;

    @NotNull(message = "La ciudad es obligatoria.")
    private Long cityId;
}
