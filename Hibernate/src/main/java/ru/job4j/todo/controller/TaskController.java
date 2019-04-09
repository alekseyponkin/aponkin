package ru.job4j.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.ServiceTaskHibernateImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Class TaskController.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 02.04.2019.
 */
@WebServlet(urlPatterns = "/task")
public class TaskController extends HttpServlet {

    /**
     * Factory session hibernate.
     */
    private SessionFactory sessionFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.sessionFactory = (SessionFactory) config.getServletContext().getAttribute("sessionFactory");
        super.init(config);
    }

    /**
     * Get list all Task or list not done task.
     * @param req Http servlet request.
     * @param resp Http servlet response.
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String done = req.getParameter("done");
        List<Task> tasks;
        ServiceTaskHibernateImpl serviceTask = new ServiceTaskHibernateImpl(sessionFactory);
        if (done.equals("notDone")) {
            tasks = serviceTask.findAllByNotDone();
        } else {
            tasks = serviceTask.findAll();
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(mapper.writeValueAsString(tasks));
        resp.getWriter().flush();
    }

    /**
     * Add new Task.
     * @param req Http servlet request.
     * @param resp Http servlet response.
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Task task = createTaskFromJson(req);
        new ServiceTaskHibernateImpl(sessionFactory).add(task);
    }

    /**
     * Update Task.
     * @param req Http servlet request.
     * @param resp Http servlet response.
     * @throws IOException
     */
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Task task = createTaskFromJson(req);
        new ServiceTaskHibernateImpl(sessionFactory).update(task);
    }

    /**
     * Delete Task.
     * @param req Http servlet request.
     * @param resp Http servlet response.
     * @throws IOException
     */
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Task task = createTaskFromJson(req);
        new ServiceTaskHibernateImpl(sessionFactory).delete(task);
    }

    /**
     * Create Task from Json.
     * @param req  Http servlet request.
     * @return Task.
     * @throws IOException
     */
    private Task createTaskFromJson(HttpServletRequest req) throws IOException {
        String jsonTask = new BufferedReader(new InputStreamReader(req.getInputStream())).readLine();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"));
        return mapper.readValue(jsonTask, Task.class);
    }
}