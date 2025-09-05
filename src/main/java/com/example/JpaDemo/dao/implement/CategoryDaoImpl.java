package com.example.JpaDemo.dao.implement;

import com.example.JpaDemo.configs.JPAConfig;
import com.example.JpaDemo.dao.CategoryDao;
import com.example.JpaDemo.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void create(Category category) {
        //TODO
    }

    @Override
    public void update(Category category) {
        //TODO
    }

    @Override
    public Category findById(int id) {
        //TODO AUTO GENERATED
        return null;
    }

    @Override
    public List<Category> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createNamedQuery("Category.findAll", Category.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }



}
