package com.sante.store.repositories;

import com.sante.store.entities.Category;
import com.sante.store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByOrderByIdAsc(Integer status, Pageable pageable);

    Page<Product> findAllByCategoryOrderByIdAsc(Category category, Pageable pageable);

    Page<Product> findProductsByCategoryId(Long category_id, Pageable pageable);

    @Query("select p from Product as p where lower(p.name) like concat('%', lower(:name), '%')")
    Page<Product> findByName(@Param("name") String name,Pageable pageable);
}
