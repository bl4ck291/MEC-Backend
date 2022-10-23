package com.sante.store.services;

import com.sante.store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product findOne(Long id);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllInCategory(Integer category, Pageable pageable);

    Product increaseStock(Long id, Integer amount);

    Product decreaseStock(Long id, Integer amount);

    Product update(Product product);

    Product create(Product product);

    void delete(Long id);
}
