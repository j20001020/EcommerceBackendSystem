package com.kai.ecommercebackendsystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private Integer id;

    private String username;

    private String nickname;

    private String email;

    private String userImg;

    private Date createdTime;

    private Date updatedTime;
}
