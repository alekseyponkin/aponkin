package ru.job4j.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class GeneratorPhrase.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.02.2019.
 */
public class GeneratorPhrase {
    /**
     * Pool phrase.
     */
    private List<String> poolPhrase;

    /**
     * Creates a generator phrase from file with phrases.
     * @param fileName name file with phrases in folder of resources.
     * @throws IOException
     */
    public GeneratorPhrase(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("chat/" + fileName)))) {
            poolPhrase = reader.lines().collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new IOException("File phrase not found", e);
        }
    }

    /**
     * Get random phrase.
     * @return next random phrase.
     */
    public String nextPhrase() {
        Random random = new Random();
        return poolPhrase.get(random.nextInt(poolPhrase.size()));
    }
}