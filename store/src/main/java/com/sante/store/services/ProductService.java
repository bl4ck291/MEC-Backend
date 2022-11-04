package com.sante.store.services;

import com.sante.store.entities.Category;
import com.sante.store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    Product findById(Long id);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllInCategory(Category category, Pageable pageable);

    Page<Product> findByName(String name, Pageable pageable);

    Product increaseStock(Long id, Integer amount);

    Product decreaseStock(Long id, Integer amount);

    Product update(Product product);

    Product create(Product product);

    void delete(Long id);
}
