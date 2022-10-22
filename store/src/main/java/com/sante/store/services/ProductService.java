package com.sante.store.services;

import com.sante.store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductService {
    Product findOne(Long id);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllInCategory(Integer category, Pageable pageable);

    void increaseStock(Long id, Integer amount);

    void decreaseStock(Long id, Integer amount);

    Product update(Product product);

    Product create(Product product);

    void delete(Long id);
}
