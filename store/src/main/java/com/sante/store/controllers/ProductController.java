package com.sante.store.controllers;

import com.sante.store.entities.Product;
import com.sante.store.services.CategoryService;
import com.sante.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public Page<Product> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return productService.findAll(request);
    }

    @GetMapping("/products/{id}")
    public Product showOne(@PathVariable("id") Long id) {
        return productService.findOne(id);
    }

    @PostMapping("/seller/products/new")
    public ResponseEntity<?> create(@Valid @RequestBody Product product) {
        Product IdExists = productService.findOne(product.getId());
        if (IdExists != null) {
            return ResponseEntity.badRequest().body("A product with the same Id already exists");
        }
        return ResponseEntity.ok(productService.create(product));
    }

    @PostMapping("/seller/products/{id}/edit")
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @Valid @RequestBody Product product) {
        if (!id.equals(product.getId())) {
            return ResponseEntity.badRequest().body("Provided Id does not match the Id of the product");
        }
        return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("/seller/products/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
