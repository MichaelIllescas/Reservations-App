package com.reservastrenque.reservas_trenque.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una respuesta genérica de la API con un mensaje y los datos resultantes.
 */
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    private T data;
}
