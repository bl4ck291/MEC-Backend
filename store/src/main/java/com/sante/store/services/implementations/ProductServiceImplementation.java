package com.sante.store.services.implementations;

import com.sante.store.entities.Product;
import com.sante.store.repositories.ProductRepository;
import com.sante.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public Page<Product> findAllInCategory(Integer categoryId, Pageable pageable) {
        return productRepository.findAllByCategoryOrderByIdAsc(categoryId, pageable);
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        Product productToUpdate = findOne(product.getId());
        if (productToUpdate == null) {
            throw new RuntimeException("Product not found");
        }
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setManufacturer(product.getManufacturer());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setBrand(product.getBrand());
        productToUpdate.setImageUrl(product.getImageUrl());
        productToUpdate.setStock(product.getStock());
        productToUpdate.setCategory(product.getCategory());

        return productRepository.save(productToUpdate);
    }

    @Override
    public void delete(Long id) {
        Product product = findOne(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        productRepository.delete(product);
    }

    @Override
    public Product increaseStock(Long id, Integer amount) {
        Product product = findOne(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        int newStock = product.getStock() + amount;
        product.setStock(newStock);
        update(product);
        return product;
    }

    @Override
    public Product decreaseStock(Long id, Integer amount) {
        Product product = findOne(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        int newStock = product.getStock() - amount;
        if (newStock < 0) {
            throw new RuntimeException("Not enough stock");
        }
        product.setStock(newStock);
        update(product);
        return product;
    }
}
