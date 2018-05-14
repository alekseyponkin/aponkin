package ru.job4j.parser.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Class ExecSql.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 27.04.2018.
 */
public class ExecSql {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger(ExecSql.class);
    /**
     * Starting executing sql script.
     * @param nameFile name file sql script.
     * @param connection connection database.
     */
    public void start(String nameFile, Connection connection) {
        try {
            URL urlFile = ClassLoader.getSystemResource(nameFile);
            if (urlFile == null) {
                throw new IOException("File not found");
            }
            Path path = Paths.get(ClassLoader.getSystemResource(nameFile).toURI());
            try (Scanner scanner = new Scanner(path);
                Statement statement = connection.createStatement()) {
                String str;
                while (scanner.hasNextLine()) {
                    str = scanner.nextLine().trim();
                    if (str.startsWith("--")) {
                        continue;
                    }
                    if (str.endsWith(";")) {
                       str = str.substring(0, str.length() - 1);
                        statement.execute(str);
                        logger.debug("SQL request successful - {}", str);
                    }
                }
            }
        } catch (URISyntaxException e) {
            logger.error("URI error", e);
        } catch (IOException e) {
            logger.error("File not found", e);
        } catch (SQLException e) {
            logger.error("SQL request is failing", e);
        }
    }
}