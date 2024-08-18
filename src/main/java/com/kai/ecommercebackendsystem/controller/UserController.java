package com.kai.ecommercebackendsystem.controller;

import com.kai.ecommercebackendsystem.dto.AccountDto;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody AccountDto accountDto) {
        User user = userService.findByUsername(accountDto.getUsername());

        if (user == null) {
            userService.register(accountDto);
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(ResponseStatus.OK, ResponseMessage.REGISTER_SUCCESS, null));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.of(ResponseStatus.BAD_REQUEST, ResponseMessage.REGISTER_FAIL, null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody AccountDto accountDto) {
        Authentication auth = authenticateUser(accountDto.getUsername(), accountDto.getPassword());

        User user = (User) auth.getPrincipal();

        String jwt = jwtUtil.generateToken(user);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(ResponseStatus.OK, ResponseMessage.LOGIN_SUCCESS, jwt));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.of(ResponseStatus.NOT_FOUND, ResponseMessage.USER_NOT_FOUND, null));
        } else {
            UserDto userDto = user.toUserDto();
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(ResponseStatus.OK, ResponseMessage.GET_USER_BY_ID_SUCCESS, userDto));
        }
    }


    private Authentication authenticateUser(String username, String password) {
        Authentication token = new UsernamePasswordAuthenticationToken(
                username,
                password
        );
        return authenticationManager.authenticate(token);
    }
}
