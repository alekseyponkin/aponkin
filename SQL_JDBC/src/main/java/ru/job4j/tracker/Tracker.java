package ru.job4j.tracker;

import ru.job4j.tracker.db.ConnectDataBase;
import ru.job4j.tracker.db.ExecSql;
import ru.job4j.tracker.entity.Comment;
import ru.job4j.tracker.entity.Item;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class Tracker.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 17.09.2017
 */
public class Tracker implements AutoCloseable {
    /**
     * Connect database.
     */
    private ConnectDataBase db;

    /**
     * Default constructor.
     */
    public Tracker() {
        db = new ConnectDataBase();
        createDb();
    }

    /**
     * Initialise database.
     */
    private void createDb() {
        String scriptCreateDb;
        Properties properties = new Properties();
        try (InputStream is = ClassLoader.getSystemResourceAsStream("tracker/tracker.properties")) {
            properties.load(is);
            scriptCreateDb = properties.getProperty("sql.createDb");
            try (Connection connection = db.getConnection()) {
                new ExecSql().start(scriptCreateDb, connection);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Erase data in database.
     */
    private void eraseDb() {
        String scriptEraseDb;
        Properties properties = new Properties();
        try (InputStream is = ClassLoader.getSystemResourceAsStream("tracker/tracker.properties")) {
            properties.load(is);
            scriptEraseDb = properties.getProperty("sql.eraseDb");
            try (Connection connection = db.getConnection()) {
                new ExecSql().start(scriptEraseDb, connection);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add item.
     * @param item to be added.
     */
    public int add(Item item) {
        int result = 0;
        try (Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO item(date, name, description) VALUES (?, ?, ?)")) {
            preparedStatement.setDate(1, item.getDateItem());
            preparedStatement.setString(2, item.getNameItem());
            preparedStatement.setString(3, item.getDescriptionItem());
            int test = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            result = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Update parameters item.
     * @param item updated.
     */
    public void update(Item item) {
        try (Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE item SET name = ?, description = ? WHERE id = ?")) {
                preparedStatement.setString(1, item.getNameItem());
                preparedStatement.setString(2, item.getDescriptionItem());
                preparedStatement.setInt(3, item.getIdItem());
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete item.
     * @param item to be deleted.
     */
    public void delete(Item item) {
        try (Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM item WHERE id = ?")) {
            preparedStatement.setInt(1, item.getIdItem());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find all items.
     * @return ArrayList added items.
     */
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (Connection connection = db.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(("SELECT * FROM item"));
            while (resultSet.next()) {
                Item item = new Item();
                item.setIdItem(resultSet.getInt("id"));
                item.setDateItem(resultSet.getDate("date"));
                item.setNameItem(resultSet.getString("name"));
                item.setDescriptionItem(resultSet.getString("description"));
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Find all items with the specified name.
     * @param key name items.
     * @return ArrayList found items with the name.
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE name LIKE ?")) {
            preparedStatement.setString(1, "%" + key + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println();
                Item item = new Item();
                item.setIdItem(resultSet.getInt("id"));
                item.setDateItem(resultSet.getDate("date"));
                item.setNameItem(resultSet.getString("name"));
                item.setDescriptionItem(resultSet.getString("description"));
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Find item with the id.
     * @param id item.
     * @return item with needed id.
     */
    public Item findById(String id) {
        Item result = null;
        try (Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE id = ?")) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Item item = new Item();
                item.setIdItem(resultSet.getInt("id"));
                item.setDateItem(resultSet.getDate("date"));
                item.setNameItem(resultSet.getString("name"));
                item.setDescriptionItem(resultSet.getString("description"));
                result = item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Find all comments for item.
     * @param id id item for searching.
     * @return list found the comments.
     */
    public List<Comment> findAllComments(String id) {
        List<Comment> result = new ArrayList<>();
        try (Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM comment WHERE item_id = ?")) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setTextComment(resultSet.getString("text"));
                result.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Add new comment for item.
     * @param comment comment for adding.
     * @param id id item to be added comment.
     */
    public void addComment(Comment comment, String id) {
        try (Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO comment(text, item_id) VALUES (?, ?)")) {
            preparedStatement.setString(1, comment.getTextComment());
            preparedStatement.setInt(2, Integer.parseInt(id));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        eraseDb();
    }
}