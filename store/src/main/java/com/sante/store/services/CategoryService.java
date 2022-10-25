package com.sante.store.services;

import com.sante.store.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> findAll();

    Category findById(Integer categoryId);

    List<Category> findByIdIn(List<Integer> categoryIdList);

    Category create(Category category);

    Category update(Category category);

    void delete(Integer categoryId);
}
