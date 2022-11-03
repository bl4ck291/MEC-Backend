package com.sante.store.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInOrderDto {
    @NotNull
    private ProductDto product;

    private OrderDto order;

    @NotNull
    private UserDto user;

}
