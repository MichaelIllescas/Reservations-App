package com.reservastrenque.reservas_trenque.products.persistence;

import com.reservastrenque.reservas_trenque.products.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
