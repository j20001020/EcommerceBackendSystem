package com.kai.ecommercebackendsystem.service.impl;

import com.kai.ecommercebackendsystem.dto.PageBean;
import com.kai.ecommercebackendsystem.dto.ProductDto;
import com.kai.ecommercebackendsystem.model.Product;
import com.kai.ecommercebackendsystem.repository.ProductRepository;
import com.kai.ecommercebackendsystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public PageBean getProductListByPage(Integer page, Integer size, Integer categoryId) {
        Pageable pageable = PageRequest.of(--page, size);

        Page<Product> productPage = getProductPage(categoryId, pageable);

        return getPageBean(productPage);
    }

    private Page<Product> getProductPage(Integer categoryId, Pageable pageable) {
        Page<Product> productPage;
        if (categoryId != null) {
            productPage = productRepository.findByCategoryId(categoryId, pageable);
        } else {
            productPage = productRepository.findAll(pageable);
        }
        return productPage;
    }

    private PageBean<Product> getPageBean(Page<Product> productPage) {
        PageBean<Product> pageBean = new PageBean<>();
        pageBean.setTotal(productPage.getTotalElements());
        pageBean.setItems(productPage.getContent());
        return pageBean;
    }
}
