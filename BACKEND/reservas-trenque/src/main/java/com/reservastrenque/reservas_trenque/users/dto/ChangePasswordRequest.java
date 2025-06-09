package com.reservastrenque.reservas_trenque.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {

    @NotBlank
    @Schema(description = "Contraseña actual", example = "miClaveAnterior123")
    private String currentPassword;

    @NotBlank
    @Schema(description = "Nueva contraseña", example = "miNuevaClave456")
    private String newPassword;

    @NotBlank
    @Schema(description = "Repetición de la nueva contraseña", example = "miNuevaClave456")
    private String repeatPassword;
}
