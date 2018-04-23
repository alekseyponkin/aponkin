package ru.job4j.optimization.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class ConnectDataBase.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 04.04.2018.
 */
public class ConnectDataBase {
    /**
     * Connect database.
     */
    Connection connection;
    /**
     * Database name.
     */
    String dbName;

    /**
     * Constructor with database name.
     * @param dbName name database.
     */
    public ConnectDataBase(String dbName) {
        this.dbName = dbName;
    }

    /**
     * Get connection database.
     * @return database connection.
     */
    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
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