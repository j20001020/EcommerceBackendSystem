package com.kai.ecommercebackendsystem.service;

import com.kai.ecommercebackendsystem.dto.AccountDto;
import com.kai.ecommercebackendsystem.model.User;

public interface UserService {

    User findByUsername(String username);

    void register(AccountDto accountDto);

    User getUserById(Integer id);
}
