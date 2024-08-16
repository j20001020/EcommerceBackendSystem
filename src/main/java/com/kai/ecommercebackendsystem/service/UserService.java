package com.kai.ecommercebackendsystem.service;

import com.kai.ecommercebackendsystem.dto.UserDto;
import com.kai.ecommercebackendsystem.model.User;

public interface UserService {

    User findByUsername(String username);

    void register(UserDto userDto);
}
