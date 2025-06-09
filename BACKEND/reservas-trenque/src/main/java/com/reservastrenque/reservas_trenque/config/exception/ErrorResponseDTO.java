package com.reservastrenque.reservas_trenque.config.exception;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estructura de respuesta de error")
public record ErrorResponseDTO(
        @Schema(description = "Mensaje de error", example = "El nombre es obligatorio")
        String error
) {}
