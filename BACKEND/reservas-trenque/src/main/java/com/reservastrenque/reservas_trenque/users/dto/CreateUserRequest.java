package com.reservastrenque.reservas_trenque.users.dto;

import com.reservastrenque.reservas_trenque.users.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * DTO utilizado para registrar un nuevo usuario con los campos mínimos visibles en el formulario.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @Email(message = "Debe ingresar un email válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    private String phone;

}
