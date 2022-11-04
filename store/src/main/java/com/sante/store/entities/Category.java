package com.sante.store.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@DynamicUpdate
public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "singularName", nullable = false)
    private String singularName;

    @Column(name = "pluralName", nullable = false)
    private String pluralName;

    @Column(name = "createTime")
    @CreationTimestamp
    private LocalDate createTime;

    @Column(name = "updateTime")
    @UpdateTimestamp
    private LocalDate updateTime;
}
