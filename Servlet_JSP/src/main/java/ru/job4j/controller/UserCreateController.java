package ru.job4j.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class UserCreateController.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 25.05.2018.
 */
public class UserCreateController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/UserCreate.jsp").forward(req, resp);
    }
}