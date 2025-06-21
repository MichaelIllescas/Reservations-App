package com.reservastrenque.reservas_trenque.products.model;

import com.reservastrenque.reservas_trenque.products.model.LodgingFeature;
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

    @ManyToOne
    @JoinColumn(name = "lodging_type_id", nullable = false)
    private LodgingType type;

    @ElementCollection(targetClass = LodgingFeature.class)
    @CollectionTable(
            name = "lodging_features",
            joinColumns = @JoinColumn(name = "lodging_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "feature")
    private Set<LodgingFeature> features = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "lodging_images",
            joinColumns = @JoinColumn(name = "lodging_id")
    )
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();


    @JoinColumn(name = "address_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToOne(cascade = CascadeType.REMOVE)
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
