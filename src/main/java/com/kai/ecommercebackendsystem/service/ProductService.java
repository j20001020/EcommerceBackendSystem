package com.kai.ecommercebackendsystem.service;

import com.kai.ecommercebackendsystem.dto.PageBean;
import com.kai.ecommercebackendsystem.dto.ProductDto;

public interface ProductService {
    void createProduct(ProductDto productDto);

    PageBean getProductListByPage(Integer page, Integer size, Integer categoryId);
}
