package com.example.JpaDemo.dao;

import com.example.JpaDemo.entity.User;

public interface UserDao {
    User get(String username);
    void insert(User user);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    User findByEmail(String email);
    void updatePassword(int userId, String newPassword);

}
