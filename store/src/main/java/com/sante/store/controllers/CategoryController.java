package com.sante.store.controllers;

import com.sante.store.entities.Category;
import com.sante.store.services.CategoryService;
import com.sante.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> showOne(@PathVariable("id") Integer categoryId) {
        Category category = categoryService.findById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/seller/categories/new")
    public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
    }

    @PutMapping("/seller/categories/edit")
    public ResponseEntity<Category> edit(@Valid @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.update(category), HttpStatus.OK);
    }

    @DeleteMapping("/seller/categories/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer Id) {
        categoryService.delete(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
