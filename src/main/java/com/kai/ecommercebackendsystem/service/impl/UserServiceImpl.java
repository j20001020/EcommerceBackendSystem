package com.kai.ecommercebackendsystem.service.impl;

import com.kai.ecommercebackendsystem.dto.AccountDto;
import com.kai.ecommercebackendsystem.dto.UserDto;
import com.kai.ecommercebackendsystem.model.User;
import com.kai.ecommercebackendsystem.repository.UserRepository;
import com.kai.ecommercebackendsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void register(AccountDto accountDto) {
        encodePassword(accountDto);
        User user = accountDto.toUser();
        userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void updateUserInfo(Integer id, UserDto userDto) {
        userRepository.updateUserInfo(id, userDto.getNickname(), userDto.getEmail());
    }

    @Override
    @Transactional
    public void updateUserImg(Integer id, String url) {
        userRepository.updateUserImg(id, url);
    }

    @Override
    @Transactional
    public void updatePassword(Integer id, String newPassword) {
        userRepository.updatePassword(id, passwordEncoder.encode(newPassword));
    }

    private void encodePassword(AccountDto accountDto) {
        accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Can't find user: " + username);
        }

        return user;
    }
}
