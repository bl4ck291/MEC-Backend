package com.sante.store.dtos;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.sante.store.entities.Product} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
    @NotNull
    private String name;
    private BigDecimal price;
    private String manufacturer;
    private String description;
    private String instructions;
    private String imageUrl;
    @NotNull
    @Min(0)
    private Integer stock;
    private Integer category;
}