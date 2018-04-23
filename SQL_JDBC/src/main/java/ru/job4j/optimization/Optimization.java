package ru.job4j.optimization;

import ru.job4j.optimization.db.EntriesDb;
import ru.job4j.optimization.xml.TransformerXslt;
import ru.job4j.optimization.xml.EntriesStax;
import ru.job4j.optimization.xml.ParserJaxb;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class Optimization.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 04.04.2018.
 */
public class Optimization implements Serializable {
    /**
     * Database name.
     */
    private String dbName;
    /**
     * Count field N.
     */
    private int n;

    /**
     * Default constructor.
     */
    public Optimization() {
    }

    /**
     * Start optimization.
     * @return sum field N.
     */
    public long start() {
        EntriesDb entriesDb = new EntriesDb(this.n, this.dbName);
        entriesDb.writeEntry();
        String pathFolder = Paths.get(dbName).toAbsolutePath().getParent().toString() + File.separator;
        new EntriesStax().writeXml(pathFolder + "1.xml", entriesDb.readEntry());
        new TransformerXslt(pathFolder + "1.xml", pathFolder + "2.xml").start();
        return new ParserJaxb().parse(pathFolder + "2.xml");
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Optimization that = (Optimization) o;
        return n == that.n
                && Objects.equals(dbName, that.dbName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dbName, n);
    }

    @Override
    public String toString() {
        return "Optimization{"
                + "dbName='"
                + dbName
                + '\''
                + ", n="
                + n
                + '}';
    }
}