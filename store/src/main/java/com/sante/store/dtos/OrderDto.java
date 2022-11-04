package com.sante.store.dtos;

import com.sante.store.entities.OrderStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    @NotNull
    @Min(0)
    private BigDecimal totalPrice;

    @NotNull
    private OrderStatus status;

    private String pickupDate;

    @NotBlank(message = "buyer name is mandatory")
    private String buyerName;

    @NotBlank(message = "buyer email is mandatory")
    @Email
    private String buyerEmail;

    @NotBlank(message = "buyer phone is mandatory")
    private String buyerPhone;


}
