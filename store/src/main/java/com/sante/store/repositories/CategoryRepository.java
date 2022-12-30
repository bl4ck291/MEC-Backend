package com.sante.store.repositories;

import com.sante.store.entities.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoryByIdInOrderByIdAsc(List<Long> categoryTypeList);

    List<Category> findAllByOrderByIdAsc();

    @Query("select c from Category as c where lower(c.pluralName) like lower(:pluralName)")
    Category categoryByPluralName(String pluralName);

}
