package com.example.JpaDemo.service;

import com.example.JpaDemo.entity.Category;
import com.example.JpaDemo.entity.User;

import java.util.List;

public interface CategoryService {
    void insert(Category category);
    List<Category> findAll();
    List<Category> listForHome(User currentUser);

}
