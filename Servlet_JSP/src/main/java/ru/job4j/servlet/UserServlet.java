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
        String id = req.getParameter("id");
        String aciton = req.getParameter("action");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        if (!name.equals("") && !login.equals("") && !email.equals("")) {
            User user = new User(name, login, email);
            if (!id.equals("")) {
                user.setId(Long.parseLong(id));
            }
            this.dispatchAction.get(aciton).apply(user);
        }
        doGet(req, resp);
    }

    /**
     * Initialisation dispatch action.
     */
    public void initDispatchAction() {
        this.dispatchAction.put("add", this.addUser());
        this.dispatchAction.put("update", this.updateUser());
        this.dispatchAction.put("delete", this.deleteUser());
    }

    /**
     * Action add user.
     * @return action.
     */
    public Function<User, Boolean> addUser() {
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