package ru.job4j.parser.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.parser.entity.Vacancy;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class VacancyDb.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 03.05.2018.
 */
public class VacancyDb {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger(VacancyDb.class);
    /**
     * Connection database.
     */
    private Connection connection;

    /**
     * Constructor Vacancy.
     * @param connection connection in database.
     */
    public VacancyDb(Connection connection) {
        this.connection = connection;
    }

    /**
     * Add new vacancy.
     * @param vacancy vacancy for adding.
     * @return 0 if adding is failing, otherwise id added vacancy.
     * @throws SQLException fail sql.
     */
    public int addVacancy(Vacancy vacancy) throws SQLException {
        int result = 0;
        try (PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM vacancy WHERE name=? AND author=? AND date=?")) {
            ps.setString(1, vacancy.getName());
            ps.setString(2, vacancy.getAuthor());
            ps.setTimestamp(3, Timestamp.valueOf(vacancy.getDate()));
            if (!ps.executeQuery().next()) {
                try (PreparedStatement ps1 = this.connection.prepareStatement("INSERT INTO vacancy(name, text, author, url, date) VALUES (?, ?, ?, ?, ?)")) {
                    ps1.setString(1, vacancy.getName());
                    ps1.setString(2, vacancy.getText());
                    ps1.setString(3, vacancy.getAuthor());
                    ps1.setString(4, vacancy.getUrl());
                    ps1.setTimestamp(5, Timestamp.valueOf(vacancy.getDate()));
                    ps1.execute();
                    result = ps.getGeneratedKeys().getInt(1);
                    vacancy.setId(result);
                    logger.debug("Add new {}", vacancy);
                }
            }
        }
        return result;
    }

    /**
     * Add list vacancy in database.
     * @param listVacancy list vacancy for adding.
     * @return count added new vacancy.
     * @throws SQLException fail sql.
     */
    public int addListVacancy(List<Vacancy> listVacancy) throws SQLException {
        int result = 0;
        for (Vacancy vacancy : listVacancy) {
            if (addVacancy(vacancy) != 0) {
                result++;
            }
        }
        return result;
    }

    /**
     * Get all vacancy from database.
     * @return list vacancy.
     * @throws SQLException fail sql.
     */
    public List<Vacancy> getAllVacancy() throws SQLException {
        List<Vacancy> result = new ArrayList<>();
        try (PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM vacancy");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Vacancy vacancy = new Vacancy();
                vacancy.setId(rs.getInt("id"));
                vacancy.setName(rs.getString("name"));
                vacancy.setText(rs.getString("text"));
                vacancy.setAuthor(rs.getString("author"));
                vacancy.setUrl(rs.getString("url"));
                vacancy.setDate(rs.getTimestamp("date").toLocalDateTime());
                result.add(vacancy);
            }
        }
        return result;
    }

    /**
     * Get date newest vacancy.
     * @return last date.
     * @throws SQLException fail sql.
     */
    public LocalDateTime getLastDate() throws SQLException {
        LocalDateTime result = null;
        try (Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT max(date) FROM vacancy")) {
            Timestamp timestamp = rs.getTimestamp(1);
            if (timestamp != null) {
                result = timestamp.toLocalDateTime();
            }
        }
        logger.debug("Date last vacancy - {}", result);
        return result;
    }
}