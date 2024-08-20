package com.kai.ecommercebackendsystem.service.impl;

import com.kai.ecommercebackendsystem.dto.ProductDto;
import com.kai.ecommercebackendsystem.model.Product;
import com.kai.ecommercebackendsystem.repository.ProductRepository;
import com.kai.ecommercebackendsystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void createProduct(ProductDto productDto) {
        Product product = productDto.toProduct();
        productRepository.save(product);
    }
}
