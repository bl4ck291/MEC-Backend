package com.sante.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.sante.store.entities.Product} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

    private Long id;

    @NotBlank(message = "product name is mandatory")
    private String name;

    @NotNull
    @Min(0)
    private BigDecimal price;

    private String manufacturer;

    private String description;

    private String instructions;

    private String imageUrl;

    @NotBlank(message = "brand is mandatory")
    private String brand;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotNull
    private Long categoryId;
}