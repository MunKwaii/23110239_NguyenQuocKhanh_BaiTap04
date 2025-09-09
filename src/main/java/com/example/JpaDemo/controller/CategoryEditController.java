package com.example.JpaDemo.controller;

import com.example.JpaDemo.entity.Category;
import com.example.JpaDemo.entity.User;
import com.example.JpaDemo.service.CategoryService;
import com.example.JpaDemo.service.implement.CategoryServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/categories/edit"})
public class CategoryEditController extends HttpServlet {
    private final CategoryService service = new CategoryServiceImpl();
    private static final int ROLE_ADMIN = 1;

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        User me = (User) req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String images = req.getParameter("images");

        Category c = service.findById(id);
        if (c != null && (me.getRoleid() == ROLE_ADMIN || c.getUser().getId() == me.getId())) {
            c.setName(name);
            c.setImages(images);
            service.update(c);
        }
        resp.sendRedirect(req.getContextPath() + "/category/list");
    }
}
