package ru.job4j.io;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;

/**
 * Class SearchFiles.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 28.01.2019.
 */
public class SearchFiles {
    /**
     * The queue for folder.
     */
    private final Queue<File> queueFolders = new LinkedList<>();
    /**
     * The list of found files.
     */
    private final List<File> findFiles = new ArrayList<>();
    /**
     * Pattern regExp extension.
     */
    private Pattern extsPatern;

    /**
     * Enum for indicate include or exclude file extension of searching.
     */
    private Ext ext;

    /**
     * Get list files with extension.
     * @param parent path.
     * @param exts extension for searching.
     * @return list files.
     */
    public List<File> files(String parent, List<String> exts) throws IOException {
        this.ext = Ext.INCLUDE;
        this.extsPatern = getPatternExt(exts);
        this.findFiles.clear();
        File parentFile = new File(parent);
        findFiles(parentFile);
        return this.findFiles;
    }

    /**
     * Get list files exclude extension.
     * @param parent path.
     * @param exclude extension for exclude.
     * @return list files.
     */
    public List<File> filesExlude(String parent, List<String> exclude) throws IOException {
        this.ext = Ext.EXCLUDE;
        this.extsPatern = getPatternExt(exclude);
        this.findFiles.clear();
        File parentFile = new File(parent);
        findFiles(parentFile);
        return this.findFiles;
    }

    /**
     * Find files.
     * @param file file for check.
     * @throws IOException
     */
    private void findFiles(File file) throws IOException {
        if (file.isDirectory()) {
            this.queueFolders.offer(file);
            findInFolder();
        } else {
            checkExt(file);
        }
    }

    /**
     * Find all file in folder end sub folder.
     */
    private void findInFolder() throws IOException {
        while (!this.queueFolders.isEmpty()) {
            File[] listFiles = this.queueFolders.poll().listFiles();
            for (File file : listFiles) {
                findFiles(file);
            }
        }
    }

    /**
     * Check extension file.
     * @param file for checking.
     */
    private void checkExt(File file) {
        switch (ext) {
            case INCLUDE:
                if (extsPatern.matcher(file.getName()).matches()) {
                    this.findFiles.add(file);
                }
                break;
            case EXCLUDE:
                if (!extsPatern.matcher(file.getName()).matches()) {
                    this.findFiles.add(file);
                }
            default:
        }
    }

    /**
     * Get patter for check extension.
     * @param exts list extension file.
     * @return pattern for check.
     */
    private Pattern getPatternExt(List<String> exts) {
        StringBuilder extsString = new StringBuilder("\\w*\\.(");
        for (String ext : exts) {
            extsString.append(ext).append("|");
        }
        extsString.append(")");
        return Pattern.compile(extsString.toString());
    }

    /**
     * Enum for include or exclude file extension of searching.
     */
    private enum Ext {
        INCLUDE, EXCLUDE
    }
}