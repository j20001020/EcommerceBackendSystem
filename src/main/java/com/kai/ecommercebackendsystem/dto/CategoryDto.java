package com.kai.ecommercebackendsystem.dto;

import com.kai.ecommercebackendsystem.model.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDto {

    @NotEmpty
    private String categoryName;

    public Category toCategory() {
        Category category = new Category();
        category.setCategoryName(this.categoryName);
        return category;
    }
}
