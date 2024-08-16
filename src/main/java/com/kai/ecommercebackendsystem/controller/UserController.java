package com.kai.ecommercebackendsystem.controller;

import com.kai.ecommercebackendsystem.dto.UserDto;
import com.kai.ecommercebackendsystem.dto.response.ApiResponse;
import com.kai.ecommercebackendsystem.dto.response.ResponseMessage;
import com.kai.ecommercebackendsystem.dto.response.ResponseStatus;
import com.kai.ecommercebackendsystem.model.User;
import com.kai.ecommercebackendsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody UserDto userDto) {
        User user = userService.findByUsername(userDto.getUsername());

        if (user == null) {
            userService.register(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseStatus.OK, ResponseMessage.REGISTER_SUCCESS, null));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(ResponseStatus.BAD_REQUEST, ResponseMessage.REGISTER_FAIL, null));
        }
    }
}
