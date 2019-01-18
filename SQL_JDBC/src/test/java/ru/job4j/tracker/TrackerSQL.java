package ru.job4j.tracker;

import ru.job4j.tracker.entity.Comment;
import ru.job4j.tracker.entity.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class TrackerSQL.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 18.01.2019
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    /**
     * Connect database.
     */
    private final Connection con;

    /**
     * Constructor with connection.
     * @param con connection of database.
     */
    public TrackerSQL(Connection con) {
        this.con = con;
    }

    @Override
    public int add(Item item) {
        int result = 0;
        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO item(date, name, description) VALUES (?, ?, ?) RETURNING id", PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, item.getDateItem());
            preparedStatement.setString(2, item.getNameItem());
            preparedStatement.setString(3, item.getDescriptionItem());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(Item item) {
        try (PreparedStatement preparedStatement = con.prepareStatement("UPDATE item SET name = ?, description = ? WHERE id = ?")) {
            preparedStatement.setString(1, item.getNameItem());
            preparedStatement.setString(2, item.getDescriptionItem());
            preparedStatement.setInt(3, item.getIdItem());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Item item) {
        try (PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM item WHERE id = ?")) {
            preparedStatement.setInt(1, item.getIdItem());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (Statement statement = con.createStatement()) {
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

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM item WHERE name LIKE ?")) {
            preparedStatement.setString(1, "%" + key + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
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

    @Override
    public Item findById(String id) {
        Item result = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM item WHERE id = ?")) {
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

    @Override
    public List<Comment> findAllComments(String id) {
        List<Comment> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM comment WHERE item_id = ?")) {
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

    @Override
    public void addComment(Comment comment, String id) {
        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO comment(text, item_id) VALUES (?, ?)")) {
            preparedStatement.setString(1, comment.getTextComment());
            preparedStatement.setInt(2, Integer.parseInt(id));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        this.con.close();
    }
}