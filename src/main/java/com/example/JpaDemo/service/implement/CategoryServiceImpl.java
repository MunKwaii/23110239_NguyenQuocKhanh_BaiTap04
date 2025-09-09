package com.example.JpaDemo.service.implement;

import com.example.JpaDemo.configs.JPAConfig;
import com.example.JpaDemo.dao.CategoryDao;
import com.example.JpaDemo.dao.implement.CategoryDaoImpl;
import com.example.JpaDemo.entity.Category;
import com.example.JpaDemo.entity.User;
import com.example.JpaDemo.service.CategoryService;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao = new CategoryDaoImpl();

    private static final int ROLE_ADMIN   = 1;
    private static final int ROLE_MANAGER = 2;

    @Override
    public void insert(Category category) { categoryDao.create(category); } // giữ để không vỡ code cũ

    @Override
    public void create(User owner, String name, String images) {
        Category c = new Category();
        c.setName(name);
        c.setImages(images);
        c.setUser(owner);          // gắn user đã login
        categoryDao.create(c);     // giao cho DAO lo transaction
    }


    @Override public List<Category> findAll() { return categoryDao.findAll(); }

    @Override
    public List<Category> listForHome(User currentUser) {
        if (currentUser.getRoleid() == ROLE_MANAGER) {
            return categoryDao.findByUserId(currentUser.getId());
        }
        // admin & user: xem tất cả
        return categoryDao.findAll();
    }

    @Override
    public List<Category> listOwned(User currentUser) {
        return categoryDao.findByUserId(currentUser.getId());
    }

    @Override public Category findById(int id) { return categoryDao.findById(id); }

    @Override public void update(Category category) { categoryDao.update(category); }

    @Override
    public void delete(int id, User currentUser) {
        Category c = categoryDao.findById(id);
        if (c == null) return;
        boolean isOwner = c.getUser() != null && c.getUser().getId() == currentUser.getId();
        boolean isAdmin = currentUser.getRoleid() == ROLE_ADMIN;
        if (isOwner || isAdmin) {
            categoryDao.deleteById(id);
        } // else: bỏ qua hoặc ném lỗi tuỳ bạn
    }
}

