package ru.job4j.controller;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SingInFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        if (request.getRequestURI().contains("/create")) {
            req.getRequestDispatcher("/WEB-INF/view/UserCreate.jsp").forward(req, resp);
        } else if (session.getAttribute("login") == null) {
            req.getRequestDispatcher("/WEB-INF/view/SingIn.jsp").forward(req, resp);
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }
}