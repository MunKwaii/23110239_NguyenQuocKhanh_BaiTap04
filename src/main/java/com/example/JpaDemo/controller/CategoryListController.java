package com.example.JpaDemo.controller;

import com.example.JpaDemo.entity.User;
import com.example.JpaDemo.service.CategoryService;
import com.example.JpaDemo.service.implement.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/categories/list"})
public class CategoryListController extends HttpServlet {
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User me = (User) req.getSession().getAttribute("user");
        req.setAttribute("categories", service.listForHome(me));
        req.getRequestDispatcher("/views/list-category.jsp").forward(req, resp);
    }
}
