package ru.job4j.dao;

import ru.job4j.model.Role;
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
            statement.execute("CREATE TABLE IF NOT EXISTS role_user ("
                    + "id SERIAL PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL UNIQUE)");
            statement.execute("INSERT INTO role_user (name) VALUES ('admin')");
            statement.execute("INSERT INTO role_user (name) VALUES ('user')");
            statement.execute("CREATE TABLE IF NOT EXISTS user_web ("
                    + "id SERIAL PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "login VARCHAR(100) NOT NULL, "
                    + "password VARCHAR(100) NOT NULL, "
                    + "email VARCHAR(100) NOT NULL, "
                    + "create_date TIMESTAMP NOT NULL, "
                    + "id_role INTEGER REFERENCES role_user(id) NOT NULL)");
            statement.execute("INSERT INTO user_web(name, login, password, email, create_date, id_role) VALUES ('Admin', 'Admin', 'Admin', 'Admin@test.com', '" + Timestamp.valueOf(LocalDateTime.now()) + "', '1')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long add(User user) {
        long result = 0;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO user_web(name, login, email, password, create_date, id_role) VALUES (?, ?, ?, ?, ?, (SELECT id FROM role_user WHERE role_user.name = ?)) RETURNING id")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, user.getRole().getName());
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
                     "UPDATE user_web SET name=?, login=?, password=?, email=?, id_role = (SELECT id FROM role_user WHERE role_user.name = ?) WHERE id=?")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            if (!user.getPassword().equals("")) {
                ps.setString(3, user.getPassword());
            }
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getRole().getName());
            ps.setLong(6, user.getId());
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
             PreparedStatement ps = connection.prepareStatement(
                     "SELECT u.id as id_user, u.name as name_user, u.login, u.email, u.create_date, r.id as id_role, r.name as name_role "
                             + "FROM user_web AS u JOIN role_user AS r "
                             + "ON u.id_role = r.id")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id_user"));
                user.setName(rs.getString("name_user"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
                user.setRole(new Role(rs.getLong("id_role"), rs.getString("name_role")));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = new User();
        try (Connection connection = this.ds.getConnection();
                     PreparedStatement ps = connection.prepareStatement(
                             "SELECT u.id as id_user, u.name as name_user, u.login, u.password, u.email, u.create_date, r.id as id_role, r.name as name_role "
                             + "FROM user_web AS u JOIN role_user AS r "
                             + "ON u.id_role = r.id WHERE u.id=?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.setId(rs.getLong("id_user"));
                result.setName(rs.getString("name_user"));
                result.setLogin(rs.getString("login"));
                result.setPassword(rs.getString("password"));
                result.setEmail(rs.getString("email"));
                result.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
                result.setRole(new Role(rs.getLong("id_role"), rs.getString("name_role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Find user by login and password.
     * @param login user.
     * @param password user.
     * @return user found.
     */
    public User findByLoginPassword(String login, String password) {
        User result = new User();
        try (Connection connection = this.ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "SELECT u.id as id_user, u.name as name_user, u.login, u.email, u.create_date, r.id as id_role, r.name as name_role "
                             + "FROM user_web AS u JOIN role_user AS r "
                             + "ON u.id_role = r.id WHERE u.login=? AND u.password=?")) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.setId(rs.getLong("id_user"));
                result.setName(rs.getString("name_user"));
                result.setLogin(rs.getString("login"));
                result.setEmail(rs.getString("email"));
                result.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
                result.setRole(new Role(rs.getLong("id_role"), rs.getString("name_role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Find all roles user.
     * @return list roles.
     */
    public List<Role> findAllRole() {
        List<Role> result = new ArrayList<>();
        try (Connection connection = this.ds.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM role_user");
            while (rs.next()) {
                result.add(new Role(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}