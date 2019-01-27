package ru.job4j.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Class RemoveForbiddenWords .
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.01.2019.
 */
public class RemoveForbiddenWords {
    /**
     * Char from InputStreamReader.
     */
    private int inChar;
    /**
     * Array abuse word.
     */
    private String[] abuse;
    /**
     * Buffer for char.
     */
    private StringBuffer sb = new StringBuffer();

    /**
     * Drop abuse word from InputStream.
     *
     * @param inputStream  {@link InputStream}
     * @param outputStream {@link OutputStream}
     * @param abuse        array abuse word.
     */
    public void dropAbuse(InputStream inputStream, OutputStream outputStream, String[] abuse) {
        this.abuse = abuse;
        try (InputStreamReader in = new InputStreamReader(inputStream);
             OutputStream out = outputStream) {
            while (!isEndStream()) {
                inChar = in.read();
                if (!Character.isLetterOrDigit((char) inChar) || inChar == -1) {
                    printGoodWord(out);
                } else {
                    sb.append((char) inChar);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print good word in
     *
     * @param out {@link OutputStream}
     * @throws IOException
     */
    private void printGoodWord(OutputStream out) throws IOException {
        boolean isGoodWord = true;
        for (String s : this.abuse) {
            if (s.equals(sb.toString())) {
                isGoodWord = false;
                break;
            }
        }
        if (isGoodWord) {
            if (!isEndStream()) {
                sb.append((char) inChar);
            }
            for (byte b : sb.toString().getBytes()) {
                out.write(b);
            }
        }
        sb.setLength(0);
    }

    /**
     * Check end {@link InputStream}
     *
     * @return {@code true} if is end {@link InputStream}, otherwise {@code false}
     */
    private boolean isEndStream() {
        return inChar == -1;
    }
}