package com.example.JpaDemo.controller;
import com.example.JpaDemo.entity.User;
import com.example.JpaDemo.service.CategoryService;
import com.example.JpaDemo.service.implement.CategoryServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;


@WebServlet(urlPatterns = {
        "/admin/home/categories/add",
        "/manager/home/categories/add",
        "/user/home/categories/add"
})
public class CategoryAddController extends HttpServlet {
    private final CategoryService service = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("formAction", req.getRequestURI());
        req.getRequestDispatcher("/views/add-category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        User me = (User) req.getSession().getAttribute("account");
        if (me == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String name = req.getParameter("name");
        String images = req.getParameter("images");

        try {
            service.create(me, name, images);

            if (me.getRoleid() == 1) {
                resp.sendRedirect(req.getContextPath() + "/admin/home/categories");
            } else if (me.getRoleid() == 2) {
                resp.sendRedirect(req.getContextPath() + "/manager/home/categories");
            } else {
                resp.sendRedirect(req.getContextPath() + "/user/home/categories");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi khi thêm: " + e.getMessage());
            req.getRequestDispatcher("/views/add-category.jsp").forward(req, resp);
        }
    }

}
