package com.sante.store.controllers;

import com.sante.store.dtos.CategoryDto;
import com.sante.store.entities.Category;
import com.sante.store.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<CategoryDto> showOne(@PathVariable("id") Integer categoryId) {
        CategoryDto category = EntityToDto(categoryService.findByIdStrict(categoryId));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/seller/categories/new")
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(EntityToDto(categoryService.create(DtoToEntity(categoryDto))), HttpStatus.CREATED);
    }

    @PutMapping("/seller/categories/edit")
    public ResponseEntity<CategoryDto> edit(@Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(EntityToDto(categoryService.update(DtoToEntity(categoryDto))), HttpStatus.OK);
    }

    @DeleteMapping("/seller/categories/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer Id) {
        categoryService.delete(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Category DtoToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        if (categoryDto.getId() != null) {
            category = categoryService.findByIdStrict(categoryDto.getId());
        }
        category.setId(categoryDto.getId());
        category.setSingularName(categoryDto.getSingularName());
        category.setPluralName(categoryDto.getPluralName());
        return category;
    }

    private CategoryDto EntityToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setSingularName(category.getSingularName());
        categoryDto.setPluralName(category.getPluralName());
        return categoryDto;
    }
}
