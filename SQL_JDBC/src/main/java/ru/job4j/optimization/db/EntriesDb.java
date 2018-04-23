package ru.job4j.optimization.db;

import ru.job4j.optimization.entity.Entries;
import ru.job4j.optimization.entity.Entry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class EntriesDb.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 13.04.2018.
 */
public class EntriesDb {
    /**
     * Count entry for write database.
     */
    private int countEntry;
    /**
     * Database name.
     */
    private String dbName;
    /**
     * Instance ConnectDataBase.
     */
    private ConnectDataBase db;
    /**
     * Connect database.
     */
    private Connection connection;

    /**
     * Constructor with count entry and database name.
     * @param countEntry count entry.
     * @param dbName database name.
     */
    public EntriesDb(int countEntry, String dbName) {
        this.countEntry = countEntry;
        this.dbName = dbName;
    }

    /**
     * Write entry in database.
     */
    public void writeEntry() {
        db = new ConnectDataBase(this.dbName);
        connection = db.getConnection();
        try {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE TABLE IF NOT EXISTS test(field NOT NULL UNIQUE)");
                if (statement.executeQuery("SELECT * FROM test LIMIT 1").next()) {
                    statement.executeUpdate("DELETE FROM test");
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO test(field) VALUES (?)")) {
                for (int i = 1; i <= countEntry; i++) {
                    preparedStatement.setInt(1, i);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                connection.commit();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Read entry from database.
     * @return list entry.
     */
    public Entries readEntry() {
        List<Entry> list = new ArrayList<>();
        db = new ConnectDataBase(this.dbName);
        connection = db.getConnection();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT field from test")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Entry entry = new Entry();
                    entry.setField(resultSet.getInt("field"));
                    list.add(entry);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Entries result = new Entries();
        result.setEntries(list);
        return result;
    }
}