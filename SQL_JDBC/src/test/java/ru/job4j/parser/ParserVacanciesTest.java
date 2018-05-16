package ru.job4j.parser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Class ParserVacanciesTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 03.05.2018.
 */
public class ParserVacanciesTest {
    ParserVacancies parser;

    @Before
    public void before() {
        parser = new ParserVacancies();
    }

    /**
     *
     * Test when start program Parser vacancy then finish program.
     */
    @Test
    public void whenStartProgramThenFinishProgram() {
        parser.start();
        parser.finish();
    }

    @After
    public void after() {
        parser.eraseDb();
    }
}