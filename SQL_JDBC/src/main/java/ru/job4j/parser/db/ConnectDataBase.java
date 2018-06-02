package ru.job4j.parser.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
     * Logger.
     */
    private static Logger logger = LogManager.getLogger(ConnectDataBase.class);
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
    public ConnectDataBase(Properties properties) {
        this.classDb = properties.getProperty("jdbc.class");
        logger.debug("Class database - {}", classDb);
        this.url = properties.getProperty("jdbc.url");
        logger.debug("Url database - {}", url);
        this.name = properties.getProperty("jdbc.name");
        logger.debug("Name database - {}", name);
        this.password = properties.getProperty("jdbc.password");
        logger.debug("Password database - {}", password);
        try {
            Class.forName(classDb);
        } catch (ClassNotFoundException e) {
            logger.error("Can't get class. No drive found.", e);
        }
    }

    /**
     * Get connection database.
     * @return database connection.
     */
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, name, password);
            logger.debug("Connection is established");
        } catch (SQLException e) {
            logger.error("Can't create connection", e);
        }
        return connection;
    }
}