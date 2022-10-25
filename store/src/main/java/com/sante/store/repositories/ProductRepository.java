package com.sante.store.repositories;

import com.sante.store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByOrderByIdAsc(Integer status, Pageable pageable);

    Page<Product> findAllByCategoryOrderByIdAsc(Integer categoryId, Pageable pageable);
    Page<Product> findAllByOrderById(Pageable pageable);
}
