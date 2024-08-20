package com.kai.ecommercebackendsystem.dto;

import com.kai.ecommercebackendsystem.model.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class ProductDto {

    private Integer id;

    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$", message = "產品名稱必須為長度1~10位")
    private String productName;

    private String description;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stockQuantity;

    @NotNull
    private Integer categoryId;

    @NotEmpty
    @URL
    private String productImg;

    private String remark;

    public Product toProduct() {
        Product product = new Product();
        product.setId(this.id);
        product.setProductName(this.productName);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setStockQuantity(this.stockQuantity);
        product.setCategoryId(this.categoryId);
        product.setProductImg(this.productImg);
        product.setRemark(this.remark);
        return product;
    }
}
