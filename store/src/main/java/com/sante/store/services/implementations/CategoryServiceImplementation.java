package com.sante.store.services.implementations;

import com.sante.store.entities.Category;
import com.sante.store.repositories.CategoryRepository;
import com.sante.store.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Category findById(Integer categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElse(null);
        if (cat == null) {
            throw new RuntimeException("Category not found");
        }
        return cat;
    }

    @Override
    public List<Category> findByIdIn(List<Integer> categoryIdList) {
        return categoryRepository.findCategoryByIdInOrderByIdAsc(categoryIdList);
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category categoryToUpdate = categoryRepository.findById(category.getId()).orElse(null);
        if (categoryToUpdate == null) {
            throw new RuntimeException("Category not found");
        }
        categoryToUpdate.setName(category.getName());
        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    public void delete(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.delete(category);
    }
}
