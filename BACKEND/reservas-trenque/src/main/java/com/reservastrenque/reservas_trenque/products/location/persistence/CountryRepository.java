package com.reservastrenque.reservas_trenque.products.location.persistence;

import com.reservastrenque.reservas_trenque.products.location.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);
}
