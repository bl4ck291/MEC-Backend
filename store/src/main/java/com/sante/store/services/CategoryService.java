package com.sante.store.services;

import com.sante.store.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findByType(Integer categoryType);

    List<Category> findByCategoryTypeIn(List<Integer> categoryTypeList);

    Category save(Category category);
}
