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

@WebServlet(urlPatterns = {
        "/admin/home/categories/delete",
        "/manager/home/categories/delete",
        "/user/home/categories/delete"
})
public class CategoryDeleteController extends HttpServlet {
    private final CategoryService service = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        User me = (User) req.getSession().getAttribute("account");

        if (me == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        service.delete(id, me);

        // Redirect về list theo role
        if (me.getRoleid() == 1) {
            resp.sendRedirect(req.getContextPath() + "/admin/home/categories");
        } else if (me.getRoleid() == 2) {
            resp.sendRedirect(req.getContextPath() + "/manager/home/categories");
        } else {
            resp.sendRedirect(req.getContextPath() + "/user/home/categories");
        }
    }
}

