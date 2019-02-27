package ru.job4j.chat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class LogFile.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 25.02.2019.
 */
public class LogFile implements AutoCloseable {
    /**
     * Logger.
     */
    private static LogFile logger;
    /**
     * Date format logger.
     */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * File writer logger.
     */
    private static FileWriter fileWriter;

    /**
     * Get single instance class LogFile.
     * @param fileName name file whits full path.
     * @return class LogFile.
     * @throws IOException
     */
    public static LogFile getInstance(String fileName) throws IOException {
        if (logger == null) {
            logger = new LogFile(fileName);
        }
        return logger;
    }

    /**
     * Private default constructor.
     */
    private LogFile() {
    }

    /**
     * Creates logger with wring to a file.
     * @param fileName file to write log.
     * @throws IOException
     */
    private LogFile(String fileName) throws IOException {
        fileWriter = new FileWriter(new File(fileName), true);
    }

    /**
     * Writing message to a file log.
     * @param message  message for record.
     * @throws IOException
     */
    public void info(String message) throws IOException {
        Date date = new Date(System.currentTimeMillis());
        String logMessage = String.format("%s [LOG] ==> %s \n", dateFormat.format(date), message);
        fileWriter.write(logMessage);
        fileWriter.flush();
    }

    @Override
    public void close() throws IOException {
        fileWriter.close();
    }
}