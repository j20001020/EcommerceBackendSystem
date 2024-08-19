package com.kai.ecommercebackendsystem.service.impl;

import com.kai.ecommercebackendsystem.dto.CategoryDto;
import com.kai.ecommercebackendsystem.model.Category;
import com.kai.ecommercebackendsystem.repository.CategoryRepository;
import com.kai.ecommercebackendsystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void createCategory(CategoryDto categoryDto) {
        Category category = categoryDto.toCategory();
        categoryRepository.save(category);
    }
}
