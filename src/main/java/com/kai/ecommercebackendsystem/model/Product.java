package com.kai.ecommercebackendsystem.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kai.ecommercebackendsystem.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@JsonPropertyOrder({"id", "productName", "description", "price", "stockQuantity", "categoryId", "productImg", "remark"})
@Table(name = "product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity = 0;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "product_img")
    private String productImg;

    @Column(name = "remark")
    private String remark;
}
