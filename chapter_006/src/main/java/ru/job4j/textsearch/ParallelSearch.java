package ru.job4j.textsearch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Class ParallelSearch.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 17.03.2018.
 */
@ThreadSafe
public class ParallelSearch {
    /**
     * The path to search for files.
     */
    private String root;
    /**
     * The text for search.
     */
    private String text;
    /**
     * The extension file where to find the text.
     */
    private final List<String> exts;
    /**
     * Flag the end of file search.
     */
    private volatile boolean finish = false;
    /**
     * The queue for adding name files from thread.
     */
    @GuardedBy("this")
    private final Queue<String> files = new LinkedList<>();
    /**
     * The list of found files.
     */
    @GuardedBy("this")
    private final List<String> paths = new ArrayList<>();

    /**
     * Constructor for class ParallerSaearch.
     * @param root the path to search for files.
     * @param text the text for search.
     * @param exts the extension file where to find the text.
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    /**
     * Parallel search text in file.
     * @return list of path file where were find the text.
     */
    public List<String> result() {
        init();
        return this.paths;
    }

    /**
     * Initialization parallel search.
     */
    private void init() {
        Thread threadSearch = new Thread(() -> {
            findFile(Paths.get(root));
                finish = true;
        });

        Thread threadRead = new Thread(() -> {
            String temp;
            synchronized (this) {
                while (!finish || !files.isEmpty()) {
                    while (!files.isEmpty()) {
                        temp = files.poll();
                        findText(temp);
                    }
                }
            }
        });
        threadSearch.start();
        threadRead.start();
        try {
            threadSearch.join();
            threadRead.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Search text in file.
     * @param fileName name file where to look.
     */
    private void findText(String fileName) {
        try (Scanner in = new Scanner(Paths.get(fileName), "UTF-8")) {
            while (in.hasNext()) {
                String line = in.nextLine();
                if (line.contains(this.text)) {
                    synchronized (this) {
                        paths.add(fileName);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find all file in folder end sub folder.
     * @param path root file for search.
     */
    private void findFile(Path path) {
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    for (String ext : exts) {
                        if (path.toString().endsWith(ext)) {
                            synchronized (this) {
                                files.add(path.toFile().getCanonicalFile().toString());
                            }
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}