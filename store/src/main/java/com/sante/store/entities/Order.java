package com.sante.store.entities;


import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicUpdate
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "totalPrice", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "status", nullable = false)
    private OrderStatus status = OrderStatus.ORDERING;

    @Column(name = "pickupDate")
    private LocalDate pickupDate;

    @Column(name = "buyerName", nullable = false)
    private String buyerName;

    @Column(name = "buyerEmail", nullable = false)
    private String buyerEmail;

    @Column(name = "buyerPhone", nullable = false)
    private String buyerPhone;

    @Column(name = "createTime")
    @CreationTimestamp
    private LocalDate createTime;

    @Column(name = "updateTime")
    @UpdateTimestamp
    private LocalDate updateTime;

}
