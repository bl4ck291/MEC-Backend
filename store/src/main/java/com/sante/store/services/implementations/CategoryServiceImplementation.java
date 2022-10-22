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
        return categoryRepository.findAllByOrderByTypeAsc();
    }

    @Override
    public Category findByType(Integer categoryType) {
        Category cat = categoryRepository.findByType(categoryType);
        if (cat == null) {
            throw new RuntimeException("Category not found");
        }
        return cat;
    }

    @Override
    public List<Category> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return categoryRepository.findCategoryByTypeInOrderByTypeAsc(categoryTypeList);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
