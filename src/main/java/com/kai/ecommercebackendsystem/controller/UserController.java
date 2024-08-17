package com.kai.ecommercebackendsystem.controller;

import com.kai.ecommercebackendsystem.dto.UserDto;
import com.kai.ecommercebackendsystem.dto.response.ApiResponse;
import com.kai.ecommercebackendsystem.dto.response.ResponseMessage;
import com.kai.ecommercebackendsystem.dto.response.ResponseStatus;
import com.kai.ecommercebackendsystem.model.User;
import com.kai.ecommercebackendsystem.service.UserService;
import com.kai.ecommercebackendsystem.utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

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

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody UserDto userDto) {
        Authentication auth = authenticateUser(userDto.getUsername(), userDto.getPassword());

        User user = (User) auth.getPrincipal();

        String jwt = jwtUtil.generateToken(user);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseStatus.OK, ResponseMessage.LOGIN_SUCCESS, jwt));
    }

    private Authentication authenticateUser(String username, String paasword) {
        Authentication token = new UsernamePasswordAuthenticationToken(
                username,
                paasword
        );
        return authenticationManager.authenticate(token);
    }
}
