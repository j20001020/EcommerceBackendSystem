package com.kai.ecommercebackendsystem.controller;

import com.kai.ecommercebackendsystem.dto.response.ApiResponse;
import com.kai.ecommercebackendsystem.dto.response.ResponseMessage;
import com.kai.ecommercebackendsystem.dto.response.ResponseStatus;
import com.kai.ecommercebackendsystem.utils.GcpUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FileController {

    @PostMapping
    public ResponseEntity<ApiResponse<String>> uploadFile(@RequestBody MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String url = GcpUtil.uploadFile(filename, file.getInputStream());
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(ResponseStatus.OK, ResponseMessage.UPLOAD_SUCCESS, url));
    }
}
