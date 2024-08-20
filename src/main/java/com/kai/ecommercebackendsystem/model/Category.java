package com.kai.ecommercebackendsystem.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kai.ecommercebackendsystem.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@JsonPropertyOrder({"id", "categoryName"})
@Table(name = "category")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;
}
