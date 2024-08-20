package com.kai.ecommercebackendsystem.service;

import com.kai.ecommercebackendsystem.dto.PageBean;
import com.kai.ecommercebackendsystem.dto.ProductDto;
import com.kai.ecommercebackendsystem.model.Product;

public interface ProductService {
    void createProduct(ProductDto productDto);

    PageBean<Product> getProductListByPage(Integer page, Integer size, Integer categoryId);

    Product getProductById(Integer id);
}
