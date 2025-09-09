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
@WebServlet("/admin/home")
public class AdminController extends HttpServlet {

    private final CategoryService categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User account = (User)req.getSession().getAttribute("account");
        req.setAttribute("categories", categoryService.listForHome(account));
        req.getRequestDispatcher("/views/admin.jsp").forward(req,resp);
    }
}
