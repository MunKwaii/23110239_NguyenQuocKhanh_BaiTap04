package com.example.JpaDemo.service.implement;

import com.example.JpaDemo.dao.CategoryDao;
import com.example.JpaDemo.dao.implement.CategoryDaoImpl;
import com.example.JpaDemo.entity.Category;
import com.example.JpaDemo.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public void insert(Category category){
        categoryDao.create(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

}
