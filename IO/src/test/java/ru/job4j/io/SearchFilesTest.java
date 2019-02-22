package ru.job4j.io;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class SearchFilesTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 28.01.2019.
 */
@Ignore
public class SearchFilesTest {
    private static final String SEPARATOR = File.separator;
    private static String tempDir;
    private static String tempDirDeep;

   @BeforeClass
   public static void init() throws IOException {
       tempDir = System.getProperty("java.io.tmpdir") + SEPARATOR + "io";
       tempDirDeep = tempDir + SEPARATOR + "xml";
       new File(tempDir).mkdir();
       new File(tempDirDeep).mkdir();
       new File(tempDir + SEPARATOR + "test.java").createNewFile();
       new File(tempDir + SEPARATOR + "test.class").createNewFile();
       new File(tempDirDeep + SEPARATOR + "pom.xml").createNewFile();
   }

    /**
     * Test when search one extension in folder.
     * @throws IOException
     */
    @Test
    public void whenSearchOneExtInFolder() throws IOException {
        List<File> foundFile = new SearchFiles().files(tempDirDeep, Arrays.asList("xml"));
        assertThat(foundFile.get(0).getName(), is("pom.xml"));
    }

    /**
     * Test when search three extension in folder and sub folder.
     * @throws IOException
     */
    @Test
    public void whenSearchThreeExtSubFolders() throws IOException {
        List<File> foundFile = new SearchFiles().files(tempDir, Arrays.asList("java", "class", "xml"));
        List<String> nameFile = foundFile.stream().map(File::getName).collect(Collectors.toList());
        assertTrue(nameFile.contains("test.class"));
        assertTrue(nameFile.contains("test.java"));
        assertTrue(nameFile.contains("pom.xml"));
    }

    /**
     * Test when search then found nothing.
     * @throws IOException
     */
    @Test
    public void whenSearchThenFoundNothing() throws IOException {
        List<File> foundFile = new SearchFiles().files(tempDir, Arrays.asList("txt"));
        assertTrue(foundFile.isEmpty());
    }
}