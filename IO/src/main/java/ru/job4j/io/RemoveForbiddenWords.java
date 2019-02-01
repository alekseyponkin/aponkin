package ru.job4j.io;

import java.io.*;

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
        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
             Writer out = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            while (in.ready()) {
                String line = in.readLine();
                for (String badWord : abuse) {
                    line = line.replaceAll(badWord, "");
                }
                out.write(line.toCharArray());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}