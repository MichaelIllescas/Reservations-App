package com.reservastrenque.reservas_trenque.products.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.reservastrenque.reservas_trenque.products.model.LodgingType;

@Repository
public interface LodgingTypeRepository extends JpaRepository<LodgingType, Long> {
}
