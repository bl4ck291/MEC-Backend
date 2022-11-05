package com.sante.store.entities;


import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;


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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Set<ProductInOrder> productInOrderSet;

    @Column(name = "totalPrice")
    private BigDecimal totalPrice;

    @Column(name = "status", nullable = false)
    private OrderStatus status = OrderStatus.ORDERING;

    @Column(name = "pickupDate")
    private LocalDate pickupDate;

    @Column(name = "createTime")
    @CreationTimestamp
    private Timestamp createTime;

    @Column(name = "updateTime")
    @UpdateTimestamp
    private Timestamp updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public void calculateTotalPrice() {
        this.totalPrice = this.productInOrderSet.stream()
                .map(ProductInOrder::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addProductInOrder(ProductInOrder productInOrder) {
        this.productInOrderSet.add(productInOrder);
        this.calculateTotalPrice();
    }

    public void removeProductInOrder(ProductInOrder productInOrder) {
        this.productInOrderSet.remove(productInOrder);
        this.calculateTotalPrice();
    }

}
