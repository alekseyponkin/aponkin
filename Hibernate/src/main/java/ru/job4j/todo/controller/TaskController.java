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

    public void init(ServletConfig config) throws ServletException {
        this.sessionFactory = (SessionFactory) config.getServletContext().getAttribute("sessionFactory");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String done = request.getParameter("done");
        List<Task> tasks;
        ServiceTaskHibernateImpl serviceTask = new ServiceTaskHibernateImpl(sessionFactory);
        if (done.equals("false")) {
            tasks = serviceTask.findAllByNotDone();
        } else {
            tasks= serviceTask.findAll();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(tasks));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonTask = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
        ObjectMapper mapper = new ObjectMapper();
        Task task = mapper.readValue(jsonTask, Task.class);
        new ServiceTaskHibernateImpl(sessionFactory).add(task);
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonTask = new BufferedReader(new InputStreamReader(req.getInputStream())).readLine();
        ObjectMapper mapper = new ObjectMapper();
        Task task = mapper.readValue(jsonTask, Task.class);
        new ServiceTaskHibernateImpl(sessionFactory).update(task);
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonTask = new BufferedReader(new InputStreamReader(req.getInputStream())).readLine();
        ObjectMapper mapper = new ObjectMapper();
        Task task = mapper.readValue(jsonTask, Task.class);
        new ServiceTaskHibernateImpl(sessionFactory).delete(task);
    }
}