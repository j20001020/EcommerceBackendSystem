package com.kai.ecommercebackendsystem.dto;

import com.kai.ecommercebackendsystem.model.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

@Data
public class CategoryDto {

    @NotNull(groups = Update.class)
    private Integer id;

    @NotEmpty
    private String categoryName;

    public Category toCategory() {
        Category category = new Category();
        category.setId(this.id);
        category.setCategoryName(this.categoryName);
        return category;
    }

    public interface Create extends Default {}

    public interface Update extends Default {}
}
