package com.kai.ecommercebackendsystem.service;

import com.kai.ecommercebackendsystem.dto.CategoryDto;
import com.kai.ecommercebackendsystem.model.Category;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDto categoryDto);

    List<Category> getAllCategory();

    Category findById(Integer id);
}
