package com.sante.store.repositories;

import com.sante.store.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findCategoryByTypeInOrderByTypeAsc(List<Integer> categoryTypeList);

    List<Category> findAllByOrderByTypeAsc();

    Category findByType(Integer categoryType);
}
