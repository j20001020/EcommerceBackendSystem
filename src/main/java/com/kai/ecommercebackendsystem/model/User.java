package com.kai.ecommercebackendsystem.model;

import com.kai.ecommercebackendsystem.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "user_img")
    private String userImg;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
