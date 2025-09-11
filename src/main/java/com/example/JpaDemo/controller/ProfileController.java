package com.example.JpaDemo.controller;
import com.example.JpaDemo.entity.User;
import com.example.JpaDemo.service.UserService;
import com.example.JpaDemo.service.implement.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
@WebServlet(urlPatterns = {"/admin/home/profile", "/manager/home/profile", "/user/home/profile"})
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 10*1024*1024, maxRequestSize = 50*1024*1024)
public class ProfileController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        User me = (User) req.getSession().getAttribute("account");
        if (me == null) me = (User) req.getSession().getAttribute("user");
        if (me == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Lấy entity managed từ DB
        User dbUser = userService.findById(me.getId());
        if (dbUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String fullname = req.getParameter("fullname");
        String phone    = req.getParameter("phone");

        Part filePart = req.getPart("avatar");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            filePart.write(uploadPath + File.separator + fileName);
            dbUser.setAvatar(fileName);
        }

        dbUser.setFullName(fullname);
        dbUser.setPhone(phone);

        String base = req.getRequestURI();

        userService.update(dbUser);
        req.getSession().setAttribute("account", dbUser);
        req.getSession().setAttribute("user", dbUser);

        if (dbUser.getRoleid() == 1) {
            resp.sendRedirect(req.getContextPath() + "/admin/home/profile");
        } else if (dbUser.getRoleid() == 2) {
            resp.sendRedirect(req.getContextPath() + "/manager/home/profile");
        } else {
            resp.sendRedirect(req.getContextPath() + "/user/home/profile");
        }
    }
}

