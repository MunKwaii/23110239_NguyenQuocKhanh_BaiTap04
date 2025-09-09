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

    CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public void insert(Category category){
        categoryDao.create(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
    @Override
    public List<Category> listForHome(User currentUser) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            int role = currentUser.getRoleid();
            if (role == 2) {
                return em.createQuery(
                                "SELECT c FROM Category c WHERE c.user.id = :uid", Category.class)
                        .setParameter("uid", currentUser.getId())
                        .getResultList();
            }
            return em.createQuery("SELECT c FROM Category c", Category.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}
