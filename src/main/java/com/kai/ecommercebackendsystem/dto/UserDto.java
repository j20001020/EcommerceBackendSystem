package com.kai.ecommercebackendsystem.dto;

import com.kai.ecommercebackendsystem.model.User;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {

    @Pattern(regexp = "^\\S{5,16}$", message = "帳號必須為長度6~16位")
    private String username;

    @Pattern(regexp = "^\\S{5,16}$", message = "密碼必須為長度6~16位")
    private String password;

    public User toUser() {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        return user;
    }
}
