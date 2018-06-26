package ru.job4j.controller;

import ru.job4j.model.User;
import ru.job4j.validate.ValidateMemoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class SingInController.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.06.2018.
 */
public class SingInController extends HttpServlet {
    /**
     * Validator.
     */
    private final ValidateMemoryService validate = ValidateMemoryService.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(!login.equals("") && !password.equals("")) {
            User user = validate.findByLoginPassword(login, password);
            if(user.getId() != 0) {
                session.setAttribute("login", user.getLogin());
                session.setAttribute("role", user.getRole());
                resp.sendRedirect(req.getContextPath() + "/");
            }
        }
        req.setAttribute("error", "Incorrect username and/or password!");
        req.getRequestDispatcher("/WEB-INF/view/SingIn.jsp").forward(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
