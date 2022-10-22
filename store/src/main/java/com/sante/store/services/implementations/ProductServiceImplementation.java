package com.sante.store.services.implementations;

import com.sante.store.entities.Product;
import com.sante.store.repositories.ProductRepository;
import com.sante.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product findOne(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAllByOrderById(pageable);
    }

    @Override
    public Page<Product> findAllInCategory(Integer category, Pageable pageable) {
        return productRepository.findAllByCategoryOrderByIdAsc(category, pageable);
    }

    @Override
    @Transactional
    public void increaseStock(Long id, Integer amount) {
        Product product = findOne(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        int newStock = product.getStock() + amount;
        product.setStock(newStock);
        update(product);
    }

    @Override
    public void decreaseStock(Long id, Integer amount) {
        Product product = findOne(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        int newStock = product.getStock() - amount;
        if (newStock <= 0) {
            throw new RuntimeException("Not enough stock");
        }
        product.setStock(newStock);
        update(product);
    }

    @Override
    public Product update(Product product) {
        Product productToUpdate = findOne(product.getId());
        if (productToUpdate == null) {
            throw new RuntimeException("Product not found");
        }
        return productRepository.save(product);
    }

    @Override
    public Product create(Product product) {
        Product productToCheck = findOne(product.getId());
        if (productToCheck != null) {
            throw new RuntimeException("Product already exists");
        }
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product product = findOne(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        productRepository.delete(product);
    }
}
