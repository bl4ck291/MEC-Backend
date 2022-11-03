package com.sante.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * A DTO for the {@link com.sante.store.entities.Category} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @NotBlank(message = "category singular name is mandatory")
    private String singularName;

    @NotBlank(message = "category plural name is mandatory")
    private String pluralName;

}
