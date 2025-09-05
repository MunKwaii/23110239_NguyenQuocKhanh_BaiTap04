package com.example.JpaDemo.service;

import com.example.JpaDemo.entity.Category;

import java.util.List;

public interface CategoryService {
    void insert(Category category);
    List<Category> findAll();
}
