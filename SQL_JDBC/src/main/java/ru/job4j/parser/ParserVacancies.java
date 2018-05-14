package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.parser.db.ConnectDataBase;
import ru.job4j.parser.db.ExecSql;
import ru.job4j.parser.db.VacancyDb;
import ru.job4j.parser.entity.Vacancy;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * Class ParserVacancies.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 30.04.2018.
 */
public class ParserVacancies {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger(ParserVacancies.class);
    /**
     * Connect database.
     */
    private ConnectDataBase db;

    ScheduledExecutorService scheduler;
    /**
     * Setting for parser.
     */
    private Properties properties;

    /**
     * Default constructor.
     */
    public ParserVacancies() {
        try (InputStream is = ClassLoader.getSystemResourceAsStream("parser/parser.properties")) {
            if (is != null) {
                this.properties = new Properties();
                this.properties.load(is);
                db = new ConnectDataBase(this.properties);
                createDb();
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            logger.error("File properties not found", e);
        }
    }

    /**
     * Start program parser site sql.ru
     */
    public void start() {
        logger.info("Start program ParserVacancy");
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        final int periodStart = Integer.parseInt(properties.getProperty("settings.parse.period.hours"));
        this.scheduler.scheduleWithFixedDelay((Runnable) () -> {
            parse();
            logger.info("Next start after {} hours", periodStart);
            }, 0, periodStart, TimeUnit.HOURS);
    }

    /**
     * Finish program parser site sql.ru
     */
    public void finish() {
        this.scheduler.shutdown();
        logger.info("Finish program ParserVacancy");
    }

    /**
     * Parse vacancy.
     */
    public void parse() {
        try (Connection connection = db.getConnection()) {
            logger.info("Start parsing vacancy");
            VacancyDb vacancyDb = new VacancyDb(connection);
            LocalDateTime dateTime = vacancyDb.getLastDate();
            if (dateTime == null) {
                dateTime = LocalDateTime.of(LocalDate.now().withDayOfYear(1), LocalTime.of(0, 0));
            }
            List<Vacancy> listVacancy = new  ParserJsop().start("http://www.sql.ru/forum/job-offers", dateTime);
            listVacancy.addAll(new ParserJsop().start("http://www.sql.ru/forum/job", dateTime));
            int countAddedVacancy = vacancyDb.addListVacancy(listVacancy);
            logger.info("Finish parsing vacancy. New vacancies added - {}", countAddedVacancy);
        } catch (SQLException e) {
            logger.error("Parsing vacancy is failing", e);
        }

    }

    /**
     * Get all vacancy from database.
     * @return list vacancy.
     */
    public List<Vacancy> getAllVacancy() {
        List<Vacancy> result = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            VacancyDb vacancyDb = new VacancyDb(connection);
            result = vacancyDb.getAllVacancy();
        } catch (SQLException e) {
            logger.error("Getting all vacancy is failing", e);
        }
        logger.debug("In database {} vacancy", result.size());
        return result;
    }

    /**
     * Initialise database.
     */
    private void createDb() {
        String scriptCreateDb = this.properties.getProperty("sql.createDb");
        try (Connection connection = db.getConnection()) {
            new ExecSql().start(scriptCreateDb, connection);
        } catch (SQLException e) {
            logger.error("Create database is failing", e);
        }
    }

    /**
     * Erase data in database.
     */
    public void eraseDb() {
        String scriptEraseDb = this.properties.getProperty("sql.eraseDb");
        try (Connection connection = db.getConnection()) {
            new ExecSql().start(scriptEraseDb, connection);
        } catch (SQLException e) {
            logger.error("Erase database is failing", e);

        }
    }

    public static void main(String[] args) {
        new ParserVacancies().start();
    }
}