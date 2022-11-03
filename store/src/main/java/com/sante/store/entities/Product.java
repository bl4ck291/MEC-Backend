package com.sante.store.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicUpdate
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "description")
    private String description;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "stock", columnDefinition = "integer not null check(stock >= 0)")
    private Integer stock;

    @Column(name = "createTime")
    @CreationTimestamp
    private LocalDate createTime;

    @Column(name = "updateTime")
    @UpdateTimestamp
    private LocalDate updateTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Category category;
}
