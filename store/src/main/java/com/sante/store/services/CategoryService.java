package com.sante.store.services;

import com.sante.store.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    List<Category> findAll();

    Optional<Category> findById(Integer categoryId);

    Category findByIdStrict(Integer categoryId);

    List<Category> findByIdIn(List<Integer> categoryIdList);

    Category create(Category category);

    Category update(Category category);

    void delete(Integer categoryId);

    Category getReference(Integer categoryId);
}
