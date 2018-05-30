package ru.job4j.servlet;

import ru.job4j.model.User;
import ru.job4j.validate.ValidateMemoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Class UserServlet.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 18.05.2018.
 */
public class UserServlet extends HttpServlet {
    /**
     * Validator.
     */
    private final ValidateMemoryService validate = ValidateMemoryService.getInstance();
    /**
     * Dispatch action.
     */
    private final Map<String, Function<User, Boolean>> dispatchAction = new HashMap<>();

    @Override
    public void init() throws ServletException {
        this.initDispatchAction();
        super.init();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.println("<h1> List users </h1>");
        for (User user : this.validate.findAll()) {
           pw.println(user + "</br>");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User();
        if (id != null) {
            user.setId(Long.parseLong(id));
        }
        if (action.equals("delete")) {
            System.out.println(user);
            this.dispatchAction.get(action).apply(user);
        } else {
            if (!name.equals("") && !login.equals("") && !email.equals("")) {
                user.setName(name);
                user.setLogin(login);
                user.setEmail(email);
                this.dispatchAction.get(action).apply(user);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/list");
    }

    /**
     * Initialisation dispatch action.
     */
    private void initDispatchAction() {
        this.dispatchAction.put("add", this.addUser());
        this.dispatchAction.put("update", this.updateUser());
        this.dispatchAction.put("delete", this.deleteUser());
    }

    /**
     * Action add user.
     * @return action.
     */
    private Function<User, Boolean> addUser() {
        return this.validate::add;
    }
    /**
     * Action update user.
     * @return action.
     */
    private Function<User, Boolean> updateUser() {
        return this.validate::update;
    }
    /**
     * Action delete user.
     * @return action.
     */
    private Function<User, Boolean> deleteUser() {
        return this.validate::delete;
    }
}