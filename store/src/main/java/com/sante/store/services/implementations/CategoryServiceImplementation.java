package com.sante.store.services.implementations;

import com.sante.store.entities.Category;
import com.sante.store.repositories.CategoryRepository;
import com.sante.store.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category findByIdStrict(Long categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElse(null);
        if (cat == null) {
            throw new RuntimeException("Category not found");
        }
        return cat;
    }

    @Override
    public List<Category> findByIdIn(List<Long> categoryIdList) {
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
        categoryToUpdate.setSingularName(category.getSingularName());
        categoryToUpdate.setPluralName(category.getPluralName());
        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    public void delete(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.delete(category);
    }

    @Override
    public Category getReference(Long categoryId) {
        return categoryRepository.getReferenceById(categoryId);
    }
}
