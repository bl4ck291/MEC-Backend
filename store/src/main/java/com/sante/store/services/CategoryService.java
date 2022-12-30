package com.sante.store.services;

import com.sante.store.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    List<Category> findAll();

    Optional<Category> findById(Long categoryId);

    Category findByIdStrict(Long categoryId);

    List<Category> findByIdId(List<Long> categoryIdList);

    Category create(Category category);

    Category update(Category category);

    void delete(Long categoryId);

    Category getReference(Long categoryId);

    Category getCategory(String pluralName);

}
