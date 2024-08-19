package com.kai.ecommercebackendsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    @NotNull
    private Integer id;

    private String username;

    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$", message = "暱稱必須為長度1~10位")
    private String nickname;

    @NotEmpty
    @Email
    private String email;

    private String userImg;

    private Date createdTime;

    private Date updatedTime;
}
