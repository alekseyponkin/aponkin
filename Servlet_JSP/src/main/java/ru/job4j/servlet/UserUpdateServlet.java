package ru.job4j.servlet;

import ru.job4j.model.User;
import ru.job4j.validate.ValidateMemoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class UserUpdateServlet.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 25.05.2018.
 */
public class UserUpdateServlet extends HttpServlet {
    /**
     * Validator.
     */
    private final ValidateMemoryService validate = ValidateMemoryService.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder formUpdate = new StringBuilder();
        if (req.getParameter("id") != null) {
            User user = this.validate.findById(Long.parseLong(req.getParameter("id")));
            formUpdate.append("<form action = '" + req.getContextPath() + "/users' method = 'post'>");
            formUpdate.append("<p><b>Name</b><br>");
            formUpdate.append("<input type = 'hidden' name = 'action' value = 'update'>");
            formUpdate.append("<input type = 'hidden' name = 'id' value = '" + user.getId() + "'>");
            formUpdate.append("<input type = 'text' name = 'name' value = '" + user.getName() + "'><br>");
            formUpdate.append("<p><b>Login</b><br>");
            formUpdate.append("<input type = 'text' name = 'login' value = '" + user.getLogin() + "'><br>");
            formUpdate.append("<p><b>Email</b><br>");
            formUpdate.append("<input type = 'email' name = 'email' value = '" + user.getEmail() + "'><br>");
            formUpdate.append("<br>");
            formUpdate.append("<input type = 'submit' value = 'Update'>");
            formUpdate.append("</form>");
            formUpdate.append("</td>");
            formUpdate.append("</tr>");
            resp.setContentType("text/html");
        }
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Update users</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Update user</h1>"
                + formUpdate
                + "\n"
                + "</body>\n"
                + "</html>");
        pw.flush();
    }
}