package ru.job4j.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Class CheckStream.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.01.2019.
 */
public class CheckStream {

    /**
     * Checks that the @InputStream contains an even number.
     * @param in {@link InputStream}.
     * @return true if number is the even.
     */
    boolean isNumber(InputStream in) {
        boolean result = false;
        try (InputStream inputStream = in) {
            Scanner buffer = new Scanner(inputStream);
            if (buffer.nextInt() % 2 == 0) {
               result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static void main(String[] args) throws IOException {
//        System.out.println(new CheckStream().isNumber(System.in));
//    }
}