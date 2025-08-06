package com.reservastrenque.reservas_trenque.products.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {

    @NotBlank(message = "El título es obligatorio.")
    private String title;

    private String description;

    private String imageUrl;
}
