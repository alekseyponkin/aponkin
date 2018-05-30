package ru.job4j.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class UserCreateServlet.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 25.05.2018.
 */
public class UserCreateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder formCreate = new StringBuilder();
        formCreate.append("<form action = '" + req.getContextPath() + "/users' method = 'post'>");
        formCreate.append("<p><b>Name</b><br>");
        formCreate.append("<input type = 'hidden' name = 'action' value = 'add'>");
        formCreate.append("<input type = 'text' name = 'name'><br>");
        formCreate.append("<p><b>Login</b><br>");
        formCreate.append("<input type = 'text' name = 'login'><br>");
        formCreate.append("<p><b>Email</b><br>");
        formCreate.append("<input type = 'email' name = 'email'><br>");
        formCreate.append("<br>");
        formCreate.append("<input type = 'submit' value = 'Add'>");
        formCreate.append("</form>");
        formCreate.append("</td>");
        formCreate.append("</tr>");
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Add new users</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Add new user</h1>"
                + formCreate
                + "\n"
                + "</body>\n"
                + "</html>");
        pw.flush();
    }
}