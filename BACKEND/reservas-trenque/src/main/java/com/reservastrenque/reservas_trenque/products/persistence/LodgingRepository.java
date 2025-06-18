package com.reservastrenque.reservas_trenque.products.persistence;

import com.reservastrenque.reservas_trenque.products.model.Lodging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LodgingRepository extends JpaRepository<Lodging, Long> {

    List<Lodging> findByResponsible_Id(Long responsibleId);
}