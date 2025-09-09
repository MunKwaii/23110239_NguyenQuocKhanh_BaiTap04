package com.example.JpaDemo.controller;

import com.example.JpaDemo.entity.Category;
import com.example.JpaDemo.entity.User;
import com.example.JpaDemo.service.CategoryService;
import com.example.JpaDemo.service.implement.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {
        "/admin/home/categories/edit",
        "/manager/home/categories/edit",
        "/user/home/categories/edit"
})
public class CategoryEditController extends HttpServlet {
    private final CategoryService service = new CategoryServiceImpl();
    private static final int ROLE_ADMIN = 1;

    // Hiển thị form edit
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        Category c = service.findById(id);

        if (c == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy category");
            return;
        }

        req.setAttribute("category", c);
        req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
    }

    // Cập nhật
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        User me = (User) req.getSession().getAttribute("account"); // nhớ dùng "account"
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String images = req.getParameter("images");

        Category c = service.findById(id);

        if (c != null && (me.getRoleid() == ROLE_ADMIN || (c.getUser() != null && c.getUser().getId() == me.getId()))) {
            // Nếu input rỗng thì giữ nguyên ảnh cũ
            if (images == null || images.trim().isEmpty()) {
                images = c.getImages();
            }

            c.setName(name);
            c.setImages(images);
            service.update(c);
        }

        // Redirect về list đúng role
        if (me.getRoleid() == 1) {
            resp.sendRedirect(req.getContextPath() + "/admin/home/categories");
        } else if (me.getRoleid() == 2) {
            resp.sendRedirect(req.getContextPath() + "/manager/home/categories");
        } else {
            resp.sendRedirect(req.getContextPath() + "/user/home/categories");
        }
    }
}


