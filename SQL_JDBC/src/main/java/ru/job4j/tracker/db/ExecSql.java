package ru.job4j.tracker.db;

import java.io.IOException;
import java.net.URISyntaxException;
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
     * Starting executing sql script.
     * @param nameFile name file sql script.
     * @param connection connection database.
     */
    public void start(String nameFile, Connection connection) {
        try {
            Path path = Paths.get(ClassLoader.getSystemResource("tracker/" + nameFile).toURI());
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
                    }
                }
            }
        } catch (URISyntaxException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}