package com.sante.store.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInOrderDto {
    @NotNull
    private Long Id;

    private Long productId;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer count;

    @NotNull
    private String brand;

    private String imageURL;

    @NotNull
    private BigDecimal totalPrice;

    private Long orderId;

    @NotNull
    private Long userId;

}
