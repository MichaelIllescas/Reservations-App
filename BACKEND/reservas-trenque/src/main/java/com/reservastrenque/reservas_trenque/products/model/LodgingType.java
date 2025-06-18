package com.reservastrenque.reservas_trenque.products.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lodging_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LodgingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;     // Ej: "Cabaña", "Hotel", etc.

    private String icon;     // Ej: "fa-hotel" (FontAwesome), si se usa

    private Boolean active;  // Para ocultar tipos sin eliminar
}
