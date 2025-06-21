package com.reservastrenque.reservas_trenque.products.persistence;

import com.reservastrenque.reservas_trenque.products.model.Lodging;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LodgingRepository extends JpaRepository<Lodging, Long> {

    List<Lodging> findByResponsible_Id(Long responsibleId);

    Optional<Lodging> findByName(@NotBlank(message = "El nombre no puede estar vacío.") String name);
}