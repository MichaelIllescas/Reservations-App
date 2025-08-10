package com.reservastrenque.reservas_trenque.products.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "lodgings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lodging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000, nullable = false)
    private String name;

    @Column(length = 2000, nullable = false)
    private String description;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal dailyPrice;

    @Min(value = 1, message = "La capacidad debe ser al menos de 1 persona.")
    @Column(nullable = false)
    private Integer capacity;

    // Tipo de alojamiento: ManyToOne es EAGER por default, no hace falta especificar.
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "lodging_type_id", nullable = false)
    private LodgingType type;

    // Features: relación N:M
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "lodging_features",
            joinColumns = @JoinColumn(name = "lodging_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id")
    )
    private Set<Feature> features = new HashSet<>();

    // Lista de URLs de imágenes. LAZY por default en ElementCollection
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "lodging_images",
            joinColumns = @JoinColumn(name = "lodging_id")
    )
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    // Address: ManyToOne, suele ser EAGER por default.
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    // Responsible: ManyToOne, EAGER por default
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "responsible_id", nullable = false)
    private Responsible responsible;

    /**
     * Limpia las colecciones antes de eliminar el alojamiento para evitar conflictos de integridad
     */
    @PreRemove
    private void preRemove() {
        features.clear();
        imageUrls.clear();
    }
}
