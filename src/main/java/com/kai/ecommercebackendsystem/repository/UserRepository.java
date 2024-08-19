package com.kai.ecommercebackendsystem.repository;

import com.kai.ecommercebackendsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.nickname = :nickname, u.email = :email, u.updatedTime = CURRENT_TIMESTAMP WHERE u.id = :id")
    void updateUserInfo(@Param("id") Integer id, @Param("nickname") String nickname, @Param("email") String email);

    @Modifying
    @Query("UPDATE User u SET u.userImg = :url, u.updatedTime = CURRENT_TIMESTAMP WHERE u.id = :id")
    void updateUserImg(Integer id, String url);

    @Modifying
    @Query("UPDATE User u SET u.password = :newPassword, u.updatedTime = CURRENT_TIMESTAMP WHERE u.id = :id")
    void updatePassword(Integer id, String newPassword);
}
