package com.example.JpaDemo.service;

import com.example.JpaDemo.entity.Category;
import com.example.JpaDemo.entity.User;

import java.util.List;

public interface CategoryService {
    void insert(Category category);            // giữ nguyên (không dùng owner)
    List<Category> findAll();
    List<Category> listForHome(User currentUser);

    // thêm
    void create(User owner, String name, String images);
    Category findById(int id);
    void update(Category category);
    void delete(int id, User currentUser);
    List<Category> listOwned(User currentUser);
}
