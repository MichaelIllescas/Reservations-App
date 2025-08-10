package com.reservastrenque.reservas_trenque.products.model;

import com.reservastrenque.reservas_trenque.products.location.model.City;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La calle no puede estar vacía.")
    private String street;

    @NotBlank(message = "El número no puede estar vacío.")
    private String number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", nullable = false)
    @NotNull(message = "La ciudad es obligatoria.")
    private City city;

    // Opcional: agregar un método utilitario para obtener provincia y país a partir de la ciudad
    public String getProvinceName() {
        return city.getProvince().getName();
    }

    public String getCountryName() {
        return city.getProvince().getCountry().getName();
    }
}
