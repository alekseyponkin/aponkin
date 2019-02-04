package ru.job4j.io;

import java.io.*;
import java.util.Arrays;

/**
 * Class RemoveForbiddenWords .
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.01.2019.
 */
public class RemoveForbiddenWords {
    /**
     * Drop abuse word from InputStream.
     *
     * @param inputStream  {@link InputStream}
     * @param outputStream {@link OutputStream}
     * @param abuse        array abuse word.
     */
    public void dropAbuse(InputStream inputStream, OutputStream outputStream, String[] abuse) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             PrintWriter writer = new PrintWriter(outputStream)) {
            reader.lines()
                    .map(s -> Arrays.stream(abuse)
                            .reduce(s, ((s1, s2) -> s1.replaceAll(s2, "")))
                    ).forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}