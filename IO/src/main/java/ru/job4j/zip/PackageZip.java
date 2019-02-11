package ru.job4j.zip;

import ru.job4j.io.SearchFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Program to pack file except the specified file extensions
 * Parameters program:
 * -d - directory fro packaging
 * -e - exclude file extension
 * -0 - output file pack
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 07.02.2019.
 */
public class PackageZip {
    private static final String SEPARATOR = File.separator;

    /**
     * Start program.
     * @param args settings program.
     */
    public static void main(String[] args) {
        packaging(new Args(args));
    }

    /**
     * Packaging with parameters.
     * @param param parameters.
     */
    private static void packaging(Args param) {
        String directory = param.getDirectory();
        String pathOuput = Paths.get(directory).getParent().toString() + SEPARATOR + param.getOutput();
        List<String> excule = param.getExcule();
        System.getProperties();
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathOuput))) {
            List<File> files = new SearchFiles().filesExlude(directory, excule);
            for (File file : files) {
               try (FileInputStream fis = new FileInputStream(file)) {
                   String pathEntry = Paths.get(directory).relativize(Paths.get(file.getPath())).toString();
                   ZipEntry entry = new ZipEntry(pathEntry);
                   zout.putNextEntry(entry);
                   byte[] buffer = new byte[fis.available()];
                   fis.read(buffer);
                   zout.write(buffer);
               }
            }
            zout.closeEntry();
            System.out.println("Package creation successful!!!");
        } catch (IOException e) {
            System.err.println("Error packaging: " + e.getMessage());
        }
    }
}