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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createProduct(@RequestBody @Validated ProductDto productDto) {
        productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(ResponseStatus.OK, ResponseMessage.CREATE_SUCCESS, null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageBean<Product>>> getProductListByPage(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false) Integer categoryId
    ) {
        PageBean<Product> pageBean = productService.getProductListByPage(page, size, categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(ResponseStatus.OK, ResponseMessage.GET_PRODUCT_LIST_SUCCESS, pageBean));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.of(ResponseStatus.NOT_FOUND, ResponseMessage.CATEGORY_NOT_FOUND, null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(ResponseStatus.OK, ResponseMessage.GET_PRODUCT_BY_ID_SUCCESS, product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateProduct(@PathVariable Integer id, @RequestBody @Validated ProductDto productDto) {
        productService.updateProduct(id, productDto);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(ResponseStatus.OK, ResponseMessage.UPDATE_SUCCESS, null));
    }
}
