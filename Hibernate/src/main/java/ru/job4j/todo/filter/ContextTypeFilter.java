package ru.job4j.todo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/task")
public class ContextTypeFilter implements Filter {

    private static final String METHOD_GET = "GET";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse respHttp = (HttpServletResponse) resp;

        if (((HttpServletRequest) req).getMethod().equals(METHOD_GET)) {
            respHttp.setContentType("application/json");
            respHttp.setCharacterEncoding("UTF-8");
        }

        chain.doFilter(req, resp);
    }
}