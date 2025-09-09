package com.example.JpaDemo.dao.implement;

import com.example.JpaDemo.configs.JPAConfig;
import com.example.JpaDemo.dao.CategoryDao;
import com.example.JpaDemo.entity.Category;
import jakarta.persistence.EntityManager;


import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public void create(Category category) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex; // ném lỗi để service/controller xử lý
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Category category) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally { em.close(); }
    }

    @Override
    public Category findById(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally { em.close(); }
    }

    @Override
    public List<Category> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Category c ORDER BY c.id ASC", Category.class)
                    .getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<Category> findByUserId(int userId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT c FROM Category c WHERE c.user.id = :uid ORDER BY c.id DESC",
                            Category.class)
                    .setParameter("uid", userId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteById(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Category c = em.find(Category.class, id);
            if (c != null) em.remove(c);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally { em.close(); }
    }
}

