package com.kai.ecommercebackendsystem.controller;

import com.kai.ecommercebackendsystem.dto.response.ApiResponse;
import com.kai.ecommercebackendsystem.dto.response.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<String>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseStatus.OK, "success", "get all product data..."));
    }
}
