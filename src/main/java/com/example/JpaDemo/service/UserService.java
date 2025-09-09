package com.example.JpaDemo.service;

import com.example.JpaDemo.entity.Category;
import com.example.JpaDemo.entity.User;

import java.util.List;

public interface UserService {
    User login(String username, String password);
    User get(String username);

    void insert(User user);
    boolean register(String email, String password, String username,
                     String fullname, String phone);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);

}
