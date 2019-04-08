package ru.job4j.todo.controller;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.IServiceTask;
import ru.job4j.todo.service.ServiceTaskHibernateImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.job4j.todo.controller.ConnectionRollback.create;

/**
 * Class TaskControllerTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 05.04.2019.
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "javax.xml.*", "org.xml.sax.*", "com.sun.xml.*", "com.sun.org.*"})
public class TaskControllerTest {

    private final static SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();
    private static SessionFactory proxyFactory;

    @Before
    public void beforeMethod() {
        proxyFactory = create(FACTORY);
    }

    @Test
    public void whenCallDoGetAllTaskThenCheckTaskInDB() throws IOException, ServletException {
        IServiceTask service = new ServiceTaskHibernateImpl(proxyFactory);

        Task task = new Task("Du something", new Timestamp(System.currentTimeMillis()));
        service.add(task);

        Task task2 = new Task("Test", new Timestamp(System.currentTimeMillis()));
        service.add(task2);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ServletConfig config = mock(ServletConfig.class);
        ServletContext context = mock(ServletContext.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out);
        when(resp.getWriter()).thenReturn(writer);
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("sessionFactory")).thenReturn(proxyFactory);
        when(req.getParameter("done")).thenReturn("all");
        TaskController controller = new TaskController();
        controller.init(config);
        controller.doGet(req, resp);
        assertTrue(out.toString().contains("\"description\":\"Du something\""));
        assertTrue(out.toString().contains("\"description\":\"Test\""));
    }

    @Test
    public void doPost() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        InputStream in = new ByteArrayInputStream("{\"description\":\"Run method doPost\",\"dueDate\":\"2019-04-21T03:33\"}".getBytes());
        ServletInputStream servlet = mock(ServletInputStream.class);
        ServletConfig config = mock(ServletConfig.class);
        ServletContext context = mock(ServletContext.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out);
        when(resp.getWriter()).thenReturn(writer);
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("sessionFactory")).thenReturn(proxyFactory);
        when(servlet.read(
                Matchers.<byte[]>any(), anyInt(), anyInt()))
                .thenAnswer((Answer<Integer>) invocationOnMock -> {
                    Object[] args = invocationOnMock.getArguments();
                    byte[] output = (byte[]) args[0];
                    int offset = (int) args[1];
                    int length = (int) args[2];
                    return in.read(output, offset, length);
                });
        when(req.getInputStream()).thenReturn(servlet);
        TaskController controller = new TaskController();
        controller.init(config);
        controller.doPost(req, resp);
        IServiceTask service = new ServiceTaskHibernateImpl(proxyFactory);
        assertThat(service.findAll().get(0).getDescription(), is("Run method doPost"));
    }

    @Test
    public void doPut() throws IOException, ServletException {
        IServiceTask service = new ServiceTaskHibernateImpl(proxyFactory);
        Task task = new Task("Du something", new Timestamp(System.currentTimeMillis()));
        Integer id = service.add(task);
        assertFalse(service.findAll().get(0).isDone());

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        String jsonTask = "{\"id\":\"" + id + "\",\"done\":true}";

        InputStream in = new ByteArrayInputStream(jsonTask.getBytes());
        ServletInputStream servlet = mock(ServletInputStream.class);
        ServletConfig config = mock(ServletConfig.class);
        ServletContext context = mock(ServletContext.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out);
        when(resp.getWriter()).thenReturn(writer);
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("sessionFactory")).thenReturn(proxyFactory);
        when(servlet.read(
                Matchers.<byte[]>any(), anyInt(), anyInt()))
                .thenAnswer((Answer<Integer>) invocationOnMock -> {
                    Object[] args = invocationOnMock.getArguments();
                    byte[] output = (byte[]) args[0];
                    int offset = (int) args[1];
                    int length = (int) args[2];
                    return in.read(output, offset, length);
                });
        when(req.getInputStream()).thenReturn(servlet);
        TaskController controller = new TaskController();
        controller.init(config);
        controller.doPut(req, resp);
        assertTrue(service.findAll().get(0).isDone());
    }

    @Test
    public void doDelete() throws IOException, ServletException {
        IServiceTask service = new ServiceTaskHibernateImpl(FACTORY);
        Task task = new Task("Run method doDelete", new Timestamp(System.currentTimeMillis()));
        Integer id = service.add(task);
        assertThat(service.findById(id).getDescription(), is("Run method doDelete"));

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        String jsonTask = "{\"id\":\"" + id + "\"}";

        InputStream in = new ByteArrayInputStream(jsonTask.getBytes());
        ServletInputStream servlet = mock(ServletInputStream.class);
        ServletConfig config = mock(ServletConfig.class);
        ServletContext context = mock(ServletContext.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out);
        when(resp.getWriter()).thenReturn(writer);
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("sessionFactory")).thenReturn(FACTORY);
        when(servlet.read(
                Matchers.<byte[]>any(), anyInt(), anyInt()))
                .thenAnswer((Answer<Integer>) invocationOnMock -> {
                    Object[] args = invocationOnMock.getArguments();
                    byte[] output = (byte[]) args[0];
                    int offset = (int) args[1];
                    int length = (int) args[2];
                    return in.read(output, offset, length);
                });
        when(req.getInputStream()).thenReturn(servlet);
        TaskController controller = new TaskController();
        controller.init(config);
        controller.doDelete(req, resp);
        assertTrue(service.findAll().isEmpty());
    }

    @After
    public void afterMethod() {
        proxyFactory.close();
    }

    @AfterClass
    public static void destroy() {
        FACTORY.close();
    }
}