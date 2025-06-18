package com.reservastrenque.reservas_trenque.products.location.persistence;

import com.reservastrenque.reservas_trenque.products.location.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByProvinceId(Long provinceId);
    Optional<City> findByNameIgnoreCaseAndProvinceId(String name, Long provinceId);

    boolean existsByNameIgnoreCaseAndProvinceId(String name, Long id);
}
