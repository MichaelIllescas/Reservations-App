package com.reservastrenque.reservas_trenque.products.persistence;

import com.reservastrenque.reservas_trenque.products.model.Lodging;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LodgingRepository extends JpaRepository<Lodging, Long> {

    @EntityGraph(attributePaths = {"imageUrls", "features", "type", "address", "responsible"})
    List<Lodging> findAll();
    List<Lodging> findByResponsible_Id(Long responsibleId);

    Optional<Lodging> findByName(@NotBlank(message = "El nombre no puede estar vacío.") String name);

    @EntityGraph(attributePaths = {"imageUrls", "features", "type", "address", "responsible"})
    @Query("SELECT l FROM Lodging l WHERE LOWER(l.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(l.address.city.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Lodging> searchByQuery(@Param("query") String query);
}