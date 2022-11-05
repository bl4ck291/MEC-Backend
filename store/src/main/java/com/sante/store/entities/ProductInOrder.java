package com.sante.store.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "imageURL", nullable = false)
    private String imageURL;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private Order order;

    public void calculateTotalPrice() {
        this.totalPrice = this.price.multiply(BigDecimal.valueOf(this.count));
    }
}
