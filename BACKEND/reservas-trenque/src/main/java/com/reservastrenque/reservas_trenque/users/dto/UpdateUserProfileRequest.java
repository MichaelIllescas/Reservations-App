package com.reservastrenque.reservas_trenque.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 * DTO utilizado para completar el perfil de usuario después del registro.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserProfileRequest {

    @NotBlank(message = "El número de documento es obligatorio")
    @Pattern(regexp = "\\d{7,10}", message = "El DNI debe tener entre 7 y 10 dígitos.")
    private String documentNumber;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{6,16}", message = "El teléfono debe tener entre 6 y 15 dígitos.")
    private String phone;

    @NotBlank(message = "La dirección es obligatoria")
    private String address;
}
