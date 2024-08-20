package com.kai.ecommercebackendsystem.controller;

import com.kai.ecommercebackendsystem.dto.PageBean;
import com.kai.ecommercebackendsystem.dto.ProductDto;
import com.kai.ecommercebackendsystem.dto.response.ApiResponse;
import com.kai.ecommercebackendsystem.dto.response.ResponseMessage;
import com.kai.ecommercebackendsystem.dto.response.ResponseStatus;
import com.kai.ecommercebackendsystem.model.Product;
import com.kai.ecommercebackendsystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(ResponseStatus.OK, ResponseMessage.CREATE_SUCCESS, null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageBean<Product>>> getProductListByPage(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false) Integer categoryId
    ) {
        PageBean pageBean = productService.getProductListByPage(page, size, categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(ResponseStatus.OK, ResponseMessage.GET_PRODUCT_LIST_SUCCESS, pageBean));
    }
}
