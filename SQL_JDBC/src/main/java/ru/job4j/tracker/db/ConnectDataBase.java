package ru.job4j.tracker.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class ConnectDataBase.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 04.04.2018.
 */
public class ConnectDataBase {
    /**
     * Class database.
     */
    private String classDb;
    /**
     * Database url.
     */
    private String url;
    /**
     * Database name.
     */
    private String name;
    /**
     * Database password.
     */
    private String password;
    /**
     * Connect database.
     */
    private Connection connection;

    /**
     * Constructor with database name.
     */
    public ConnectDataBase() {
        Properties properties = new Properties();
        try (InputStream is = ClassLoader.getSystemResourceAsStream("tracker/tracker.properties")) {
            properties.load(is);
            this.url = properties.getProperty("jdbc.url");
            this.name = properties.getProperty("jdbc.name");
            this.password = properties.getProperty("jdbc.password");
            this.classDb = properties.getProperty("jdbc.class");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get connection database.
     * @return database connection.
     */
    public Connection getConnection() {
        try {
            Class.forName(classDb);
            connection = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            System.err.println("Can't get class. No driver found");
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return connection;
    }
}