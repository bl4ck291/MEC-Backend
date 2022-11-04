package com.sante.store.controllers;

import com.sante.store.dtos.ProductDto;
import com.sante.store.entities.Product;
import com.sante.store.services.CategoryService;
import com.sante.store.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    public ResponseEntity<Page<ProductDto>> findAll(Pageable request) {

        Page<ProductDto> gottenPage = productService.findAll(request).map(this::EntityToDto);
        return new ResponseEntity<>(gottenPage, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> showOne(@PathVariable("id") Long id) {
        Product productToReturn = productService.findById(id);
        return new ResponseEntity<>(EntityToDto(productToReturn), HttpStatus.OK);
    }

    @PostMapping("/seller/products/new")
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(EntityToDto(productService.create(DtoToEntity(productDto))), HttpStatus.CREATED);
    }

    @GetMapping("/products/search/{name}")
    public ResponseEntity<Page<ProductDto>> findByProductName(@PathVariable("name") String name, Pageable request) {
        Page<ProductDto> gottenPage = productService.findByName(name, request).map(this::EntityToDto);
        return new ResponseEntity<>(gottenPage, HttpStatus.OK);
    }

    @PutMapping("/seller/products/edit")
    public ResponseEntity<ProductDto> edit(@Valid @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(EntityToDto(productService.update(DtoToEntity(productDto))), HttpStatus.OK);
    }

    @DeleteMapping("/seller/products/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/seller/products/increaseStock/{id}")
    public ResponseEntity<ProductDto> increaseStock(@PathVariable("id") Long id, @RequestParam("amount") Integer amount) {
        Product updatedProduct = productService.increaseStock(id, amount);
        return new ResponseEntity<>(EntityToDto(updatedProduct), HttpStatus.OK);
    }

    @PutMapping("/seller/products/decreaseStock/{id}")
    public ResponseEntity<ProductDto> decreaseStock(@PathVariable("id") Long id, @RequestParam("amount") Integer amount) {
        Product updatedProduct = productService.decreaseStock(id, amount);
        return new ResponseEntity<>(EntityToDto(updatedProduct), HttpStatus.OK);
    }

    private Product DtoToEntity(ProductDto productDto) {
        Product product = new Product();
        if (productDto.getId() != null) {
            product = productService.findById(productDto.getId());
        }
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setManufacturer(productDto.getManufacturer());
        product.setDescription(productDto.getDescription());
        product.setInstructions(productDto.getInstructions());
        product.setBrand(productDto.getBrand());
        product.setImageUrl(productDto.getImageUrl());
        product.setStock(productDto.getStock());
        product.setCategory(categoryService.getReference(productDto.getCategoryId()));
        return product;
    }

    private ProductDto EntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setManufacturer(product.getManufacturer());
        productDto.setDescription(product.getDescription());
        productDto.setInstructions(product.getInstructions());
        productDto.setBrand(product.getBrand());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setStock(product.getStock());
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }
}
