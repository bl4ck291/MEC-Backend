package com.sante.store.dtos;

import com.sante.store.entities.OrderStatus;
import com.sante.store.entities.ProductInOrder;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private Set<ProductInOrder> productInOrderSet;

    @Min(0)
    private BigDecimal totalPrice;

    @NotNull
    private OrderStatus status;

    private LocalDate pickupDate;

    private Long userId;

}
