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
    private Long productId;

    @NotNull
    private Integer count;

    private Long orderId;

    @NotNull
    private Long userId;

}
