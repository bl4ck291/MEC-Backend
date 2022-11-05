package com.sante.store.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    private Timestamp createTime;

    @Column(name = "updateTime")
    @UpdateTimestamp
    private Timestamp updateTime;
}
