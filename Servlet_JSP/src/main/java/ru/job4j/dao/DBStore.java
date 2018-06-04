package ru.job4j.dao;

import ru.job4j.model.User;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class DBStore.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 03.06.2018.
 */
public class DBStore implements Store<User, Long> {
    /**
     * Sing instance DBStore.
     */
    private final static DBStore OUR_INSTANCE = new DBStore();
    /**
     * Data source connection pool.
     */
    private DataSource ds;

    /**
     * Get instance MemoryStore.
     * @return single instance.
     */
    public static DBStore getInstance() {
        return OUR_INSTANCE;
    }

    /**
     * Closed constructor.
     */
    private DBStore() {
            initConnectionPool();
            initDb();
    }

    /**
     * Initialise connection pool.
     */
    private void initConnectionPool() {
        try {
         InitialContext initialContext = new InitialContext();
            this.ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/postgres");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialise database.
     */
    private void initDb() {
        try (Connection connection = this.ds.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS user_web ("
                    + "id SERIAL PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "login VARCHAR(100) NOT NULL, "
                    + "email VARCHAR(100) NOT NULL, "
                    + "create_date TIMESTAMP NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long add(User user) {
        long result = 0;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO user_web(name, login, email, create_date) VALUES (?, ?, ?, ?) RETURNING id")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE user_web SET name=?, login=?, email=? WHERE id=?")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setLong(4, user.getId());
           result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        boolean result = false;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM user_web WHERE id=?")) {
            ps.setLong(1, user.getId());
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = this.ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_web")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User findById(Long aLong) {
        User result = new User();
        try (Connection connection = this.ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_web WHERE id=?")) {
            ps.setLong(1, aLong);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.setId(rs.getLong("id"));
                result.setName(rs.getString("name"));
                result.setLogin(rs.getString("login"));
                result.setEmail(rs.getString("email"));
                result.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}