package com.sante.store.controllers;

import com.sante.store.entities.Product;
import com.sante.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<Product> gottenPage = productService.findAll(request);
        return new ResponseEntity<>(gottenPage, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> showOne(@PathVariable("id") Long id) {
        Product productToReturn = productService.findOne(id);
        if(productToReturn != null) {
            return new ResponseEntity<>(productToReturn, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/seller/products/new")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
    }

    @PutMapping("/seller/products/edit")
    public ResponseEntity<Product> edit(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    @DeleteMapping("/seller/products/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/seller/products/increaseStock/{id}")
    public ResponseEntity<Product> increaseStock(@PathVariable("id") Long id, @RequestParam("amount") Integer amount) {
        Product updatedProduct = productService.increaseStock(id, amount);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @PutMapping("/seller/products/decreaseStock/{id}")
    public ResponseEntity<Product> decreaseStock(@PathVariable("id") Long id, @RequestParam("amount") Integer amount) {
        Product updatedProduct = productService.decreaseStock(id, amount);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
