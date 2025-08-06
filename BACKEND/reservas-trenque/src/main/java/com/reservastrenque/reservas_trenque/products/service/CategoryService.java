package com.reservastrenque.reservas_trenque.products.service;

import com.reservastrenque.reservas_trenque.products.dto.CategoryRequest;
import com.reservastrenque.reservas_trenque.products.dto.CategoryResponse;
import com.reservastrenque.reservas_trenque.products.model.Category;
import com.reservastrenque.reservas_trenque.products.persistence.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CategoryResponse create(CategoryRequest request) {
        Category category = Category.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
        Category saved = categoryRepository.save(category);
        return toResponse(saved);
    }

    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        category.setTitle(request.getTitle());
        category.setDescription(request.getDescription());
        Category saved = categoryRepository.save(category);
        return toResponse(saved);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }
}
