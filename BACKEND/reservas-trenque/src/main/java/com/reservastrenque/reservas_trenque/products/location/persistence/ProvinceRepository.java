package com.reservastrenque.reservas_trenque.products.location.persistence;

import com.reservastrenque.reservas_trenque.products.location.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    List<Province> findByCountryId(Long countryId);
    Optional<Province> findByNameIgnoreCaseAndCountryId(String name, Long countryId);

    boolean existsByNameIgnoreCaseAndCountryId(String name, Long id);
}
