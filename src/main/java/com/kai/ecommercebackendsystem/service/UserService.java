package com.kai.ecommercebackendsystem.service;

import com.kai.ecommercebackendsystem.dto.AccountDto;
import com.kai.ecommercebackendsystem.dto.UserDto;
import com.kai.ecommercebackendsystem.model.User;

public interface UserService {

    User findByUsername(String username);

    void register(AccountDto accountDto);

    User getUserById(Integer id);

    void updateUserInfo(Integer id, UserDto userDto);

    void updateUserImg(Integer id, String url);

    void updatePassword(Integer id, String newPassword);
}
