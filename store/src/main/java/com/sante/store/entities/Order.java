package com.sante.store.entities;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicUpdate
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
//            mappedBy = "orders"
    )
    private Set<ProductInOrder> products = new HashSet<>();

    @NotNull
    private BigDecimal totalPrice;

    @NotNull
    @ColumnDefault("0")
    private Integer status;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;

    @NotEmpty
    private String buyerName;

    @NaturalId
    @NotEmpty
    @NotNull
    @Email
    private String buyerEmail;

    @NotEmpty
    private String buyerPhone;


}
