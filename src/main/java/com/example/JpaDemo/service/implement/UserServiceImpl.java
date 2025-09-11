package com.example.JpaDemo.service.implement;

import com.example.JpaDemo.configs.JPAConfig;
import com.example.JpaDemo.dao.UserDao;
import com.example.JpaDemo.dao.implement.UserDaoImpl;
import com.example.JpaDemo.entity.Category;
import com.example.JpaDemo.entity.User;
import com.example.JpaDemo.service.UserService;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User get(String username) {
        return userDao.get(username);
    }

    @Override
    public boolean register(String username, String password, String email,
                            String fullname, String phone) {
        if (userDao.checkExistUsername(username)) {
            return false;
        }
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        userDao.insert(new User(0, email, username, fullname, password,
                null, 5, phone, date));
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }
    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

}
