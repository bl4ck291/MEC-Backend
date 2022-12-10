package com.sante.store.controllers;

import com.sante.store.dtos.CategoryDto;
import com.sante.store.entities.Category;
import com.sante.store.entities.User;
import com.sante.store.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.findAll().stream().map(this::EntityToDto).toList();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> showOne(@PathVariable("id") Long categoryId) {
        CategoryDto category = EntityToDto(categoryService.findByIdStrict(categoryId));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/seller/categories/new")
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(EntityToDto(categoryService.create(DtoToEntity(categoryDto))), HttpStatus.CREATED);
    }

    @PutMapping("/seller/categories/edit/{id}")
    public ResponseEntity<CategoryDto> edit(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("id") Long id) {
        categoryDto.setId(id);
        return new ResponseEntity<>(EntityToDto(categoryService.update(DtoToEntity(categoryDto))), HttpStatus.OK);
    }

    @DeleteMapping("/seller/categories/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long Id) {
        categoryService.delete(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Category DtoToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        if (categoryDto.getId() != null) {
            category = categoryService.findByIdStrict(categoryDto.getId());
        }
        Category existingCategory = categoryService.getCategory(categoryDto.getPluralName());
        if (existingCategory != null
                && !Objects.equals(existingCategory.getId(), category.getId())) {
            throw new RuntimeException("This category is already exists");
        }
        category.setPluralName(categoryDto.getPluralName());
        return category;
    }

    private CategoryDto EntityToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setPluralName(category.getPluralName());
        return categoryDto;
    }
}
