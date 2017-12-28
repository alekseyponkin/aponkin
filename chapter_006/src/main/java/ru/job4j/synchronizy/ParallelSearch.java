package ru.job4j.synchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class ParallelSearch.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 26.12.2017.
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
    private List<String> exts;
    /**
     * The list for adding name files from thread.
     */
    @GuardedBy("this")
    private List<String> listFiles = new ArrayList<>();

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
        ArrayList<String> result = new ArrayList<>();
        List<Thread> listThread = new ArrayList<>();
        findFile(new File(root));
        synchronized (this) {
            for (String fileName : listFiles) {
                Runnable runnable = () -> {
                    findText(fileName, result);
                };
                listThread.add(new Thread(runnable));
            }
        }
            for (Thread thread : listThread) {
                thread.start();
            }
            for (Thread thread : listThread) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        return result;
    }

    /**
     * Search text in file.
     * @param fileName name file where to look.
     * @param list list for adding name file.
     */
    private void findText(String fileName, ArrayList list) {
        try (Scanner in = new Scanner(new File(fileName), "UTF-8")) {
            while (in.hasNext()) {
                String line = in.nextLine();
                if (line.contains(this.text)) {
                    synchronized (this) {
                        list.add(fileName);
                    }
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find all file in folder end sub folder.
     * @param file root file for search.
     * @return list found name file.
     */
    private void findFile(File file) {
        if (file.isDirectory()) {
            for (File fileCurent : file.listFiles()) {
                findFile(fileCurent);
            }
        } else {
            if (checkExt(file)) {
                synchronized (this) {
                    listFiles.add(file.getPath());
                }
            }
        }
    }

    /**
     * Check the file for the extension.
     * @param file the file to check.
     * @return true if check successful.
     */
    private boolean checkExt(File file) {
        boolean result = false;
        String[] splitName = file.getName().split("\\.");
        String extFile = splitName[splitName.length - 1];
        for (String ext : exts) {
            if (ext.toLowerCase().equals(extFile.toLowerCase())) {
                result = true;
                break;
            }
        }
        return  result;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ParallelSearch parallelSearch = new ParallelSearch("C:\\projects\\aponkin", "test", Arrays.asList("java", "txt"));
        List<String> list = parallelSearch.result();
        for (String s : list) {
            System.out.println(s);
        }
        long finalTime = System.currentTimeMillis();
        System.out.println("Time program = "  + (finalTime - startTime));
    }
}