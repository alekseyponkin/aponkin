package ru.job4j.zip;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.io.SearchFiles;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class PackageZipTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.02.2019.
 */
public class PackageZipTest {
    private static final String SEPARATOR = File.separator;
    private static String tempDir;
    private static String tempDirLevelOne;
    private static String tempDirLevelTwo;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @BeforeClass
    public static void init() throws IOException {
        tempDir = System.getProperty("java.io.tmpdir");
        tempDirLevelOne = tempDir + SEPARATOR + "io";
        tempDirLevelTwo = tempDirLevelOne + SEPARATOR + "xml";
        new File(tempDirLevelOne).mkdir();
        new File(tempDirLevelTwo).mkdir();
        new File(tempDirLevelOne + SEPARATOR + "test.java").createNewFile();
        new File(tempDirLevelOne + SEPARATOR + "test.class").createNewFile();
        new File(tempDirLevelTwo + SEPARATOR + "pom.xml").createNewFile();
    }

    @Before
    public void setUpStreams() {
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Test when package zip folder then check contains the file project.zip in folder.
     * @throws IOException
     */
    @Test
    public void whenPackageZipFolderThenCheckProgectZip() throws IOException {
        new PackageZip().main(new String[]{"-d", tempDirLevelOne, "-e", "java,class", "-o", "project.zip"});
        List<File> foundFile = new SearchFiles().files(tempDir, Collections.singletonList("zip"));
        assertThat(foundFile.get(0).getName(), is("project.zip"));
    }

    /**
     * Test when package zip folder not found then check System.err message.
     */
    @Test
    public void whenPackageZipFolderNoFoundThenCheckSystemErr() {
        new PackageZip().main(new String[]{"-d", tempDirLevelOne + "test", "-e", "java,class", "-o", "project.zip"});
        assertTrue(errContent.toString().contains("Error packaging: "));
    }

    @After
    public void restoreStreamsAndRemovePackageZip() {
        System.setErr(originalErr);
        new File(tempDir + SEPARATOR + "project.zip").delete();
    }
}