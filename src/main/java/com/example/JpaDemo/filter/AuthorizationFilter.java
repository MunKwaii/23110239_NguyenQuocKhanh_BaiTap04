package com.example.JpaDemo.filter;


import com.example.JpaDemo.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*", "/manager/*", "/admin/*"})
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        User account = (session != null) ? (User) session.getAttribute("account") : null;

        if (account == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String path = req.getRequestURI();
        int role = account.getRoleid();

        if (path.startsWith(req.getContextPath() + "/admin") && role != 1) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền vào trang admin");
            return;
        }

        if (path.startsWith(req.getContextPath() + "/manager/") && role != 2) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền vào trang manager");
            return;
        }

        if (path.startsWith(req.getContextPath() + "/user/") && (role == 1 || role == 2)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền vào trang user");
            return;
        }

        chain.doFilter(request, response);
    }



}