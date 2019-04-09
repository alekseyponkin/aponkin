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
@PowerMockIgnore({ "javax.xml.*", "org.xml.sax.*", "com.sun.xml.*", "com.sun.org.*", "javax.net.ssl.*", "javax.security.*"})
public class TaskControllerTest {

    private final static SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();
    private static SessionFactory proxyFactory;

    private final static HttpServletRequest REQ = mock(HttpServletRequest.class);
    private final static HttpServletResponse RESP = mock(HttpServletResponse.class);
    private final static ServletConfig CONFIG = mock(ServletConfig.class);
    private final static ServletContext CONTEXT = mock(ServletContext.class);
    private ByteArrayOutputStream out;
    private PrintWriter writer;

    @Before
    public void beforeMethod() throws IOException {
        proxyFactory = create(FACTORY);
        out = new ByteArrayOutputStream();
        writer = new PrintWriter(out);
        when(RESP.getWriter()).thenReturn(writer);
        when(CONFIG.getServletContext()).thenReturn(CONTEXT);
        when(CONTEXT.getAttribute("sessionFactory")).thenReturn(proxyFactory);
    }

    @Test
    public void whenCallDoGetAllTaskThenCheckJson() throws IOException, ServletException {
        IServiceTask service = new ServiceTaskHibernateImpl(proxyFactory);
        service.add(new Task("Not done task", new Timestamp(System.currentTimeMillis()), false));
        service.add(new Task("Done task", new Timestamp(System.currentTimeMillis()), true));

        when(REQ.getParameter("done")).thenReturn("all");
        TaskController controller = new TaskController();
        controller.init(CONFIG);
        controller.doGet(REQ, RESP);

        assertThat(service.findAll().size(), is(2));
        assertTrue(out.toString().contains("\"description\":\"Not done task\""));
        assertTrue(out.toString().contains("\"description\":\"Done task\""));
    }

    @Test
    public void whenCallDoGetNotDoneTaskThenCheckJson() throws IOException, ServletException {
        IServiceTask service = new ServiceTaskHibernateImpl(proxyFactory);
        service.add(new Task("Not done task", new Timestamp(System.currentTimeMillis()), false));
        service.add(new Task("Done task", new Timestamp(System.currentTimeMillis()), true));

        when(REQ.getParameter("done")).thenReturn("notDone");
        TaskController controller = new TaskController();
        controller.init(CONFIG);
        controller.doGet(REQ, RESP);

        assertTrue(out.toString().contains("\"description\":\"Not done task\""));
        assertFalse(out.toString().contains("\"description\":\"Done task\""));
    }

    @Test
    public void whenCallDoPostThenCheckInDb() throws ServletException, IOException {
        IServiceTask service = new ServiceTaskHibernateImpl(proxyFactory);
        InputStream in = new ByteArrayInputStream("{\"description\":\"Run method doPost\",\"dueDate\":\"2019-04-21T03:33\"}".getBytes());
        ServletInputStream servlet = mock(ServletInputStream.class);
        when(servlet.read(
                Matchers.<byte[]>any(), anyInt(), anyInt()))
                .thenAnswer((Answer<Integer>) invocationOnMock -> {
                    Object[] args = invocationOnMock.getArguments();
                    byte[] output = (byte[]) args[0];
                    int offset = (int) args[1];
                    int length = (int) args[2];
                    return in.read(output, offset, length);
                });
        when(REQ.getInputStream()).thenReturn(servlet);

        TaskController controller = new TaskController();
        controller.init(CONFIG);
        controller.doPost(REQ, RESP);

        assertThat(service.findAll().get(0).getDescription(), is("Run method doPost"));
    }

    @Test
    public void whenCallDoPutThenCheckTaskInDb() throws IOException, ServletException {
        IServiceTask service = new ServiceTaskHibernateImpl(proxyFactory);
        Task task = new Task("Du something", new Timestamp(System.currentTimeMillis()), false);
        Integer id = service.add(task);
        assertFalse(service.findAll().get(0).isDone());

        String jsonTask = "{\"id\":\"" + id + "\",\"done\":true}";
        InputStream in = new ByteArrayInputStream(jsonTask.getBytes());
        ServletInputStream servlet = mock(ServletInputStream.class);
        when(servlet.read(
                Matchers.<byte[]>any(), anyInt(), anyInt()))
                .thenAnswer((Answer<Integer>) invocationOnMock -> {
                    Object[] args = invocationOnMock.getArguments();
                    byte[] output = (byte[]) args[0];
                    int offset = (int) args[1];
                    int length = (int) args[2];
                    return in.read(output, offset, length);
                });
        when(REQ.getInputStream()).thenReturn(servlet);

        TaskController controller = new TaskController();
        controller.init(CONFIG);
        controller.doPut(REQ, RESP);

        assertTrue(service.findAll().get(0).isDone());
    }

    @Test
    public void whenCallDoDeleteThenCheckTaskInDb() throws IOException, ServletException {
        IServiceTask service = new ServiceTaskHibernateImpl(FACTORY);
        Integer id = service.add(new Task("Run method doDelete", new Timestamp(System.currentTimeMillis()), false));
        assertThat(service.findById(id).getDescription(), is("Run method doDelete"));

        String jsonTask = "{\"id\":\"" + id + "\"}";
        InputStream in = new ByteArrayInputStream(jsonTask.getBytes());
        ServletInputStream servlet = mock(ServletInputStream.class);
        when(servlet.read(
                Matchers.<byte[]>any(), anyInt(), anyInt()))
                .thenAnswer((Answer<Integer>) invocationOnMock -> {
                    Object[] args = invocationOnMock.getArguments();
                    byte[] output = (byte[]) args[0];
                    int offset = (int) args[1];
                    int length = (int) args[2];
                    return in.read(output, offset, length);
                });
        when(REQ.getInputStream()).thenReturn(servlet);

        when(CONTEXT.getAttribute("sessionFactory")).thenReturn(FACTORY);
        TaskController controller = new TaskController();
        controller.init(CONFIG);
        controller.doDelete(REQ, RESP);

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