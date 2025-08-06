package com.reservastrenque.reservas_trenque.products.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
}
