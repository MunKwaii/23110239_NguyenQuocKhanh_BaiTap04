package com.example.JpaDemo.dao;

import com.example.JpaDemo.entity.Category;

import java.util.List;

public interface CategoryDao {
    void create(Category category);
    void update(Category category);
    Category findById(int id);
    List<Category> findAll();
    List<Category> findByUserId(int userId);
    void deleteById(int id);
}
