package com.reservastrenque.reservas_trenque.products.persistence;

import com.reservastrenque.reservas_trenque.products.model.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {
    Optional<Responsible> findByEmail(String email);
}
