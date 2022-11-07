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

    private ProductDto productDto;

    @NotNull
    private Integer count;


    @NotNull
    private BigDecimal totalPrice;

    private Long orderId;


}
