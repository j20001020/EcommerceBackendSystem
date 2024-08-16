package com.kai.ecommercebackendsystem.service.impl;

import com.kai.ecommercebackendsystem.dto.UserDto;
import com.kai.ecommercebackendsystem.model.User;
import com.kai.ecommercebackendsystem.repository.UserRepository;
import com.kai.ecommercebackendsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void register(UserDto userDto) {
        encodePassword(userDto);
        User user = userDto.toUser();
        userRepository.save(user);
    }

    private void encodePassword(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    }
}
