package com.reservastrenque.reservas_trenque.products.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "responsibles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Responsible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String documentNumber;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    private LocalDateTime registeredAt;

    private Boolean active;

    @PrePersist
    public void prePersist() {
        this.registeredAt = LocalDateTime.now();
        if (active == null) {
            this.active = true;
        }
    }
}