package com.example.JpaDemo.controller;

import com.example.JpaDemo.entity.Category;
import com.example.JpaDemo.service.CategoryService;
import com.example.JpaDemo.service.implement.CategoryServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/categories"})
public class CategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        CategoryService categoryService = new CategoryServiceImpl();
        List<Category> listCategory = categoryService.findAll();
        // tra du lieu v view
        req.setAttribute("listcate", listCategory);
        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/category-list.jsp");
        rd.forward(req, resp);
    }

}
