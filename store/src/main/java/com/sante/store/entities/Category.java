package com.sante.store.entities;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class Category implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @NaturalId
    private Integer type;

    private Date createTime;

    private Date updateTime;

    public Category() {
    }

    public Category(String name, Integer type) {
        this.name = name;
        this.type = type;
    }
}
