package ru.job4j.reference;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class SortCodesUnitsTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 18.11.2017
 */
public class SortCodesUnitsTest {

    /**
     * Test sort codes unit.
     */
    @Test
    public void whenSortCodesUnit() {
        SortCodesUnits sortCodesUnits = new SortCodesUnits();
        String[] sortCode = sortCodesUnits.sort(new String[]{"K1/SK1", "K1/SK2", "K1/SK1/SSK1", "K1/SK1/SSK2", "K2", "K2/SK1/SSK1", "K2/SK1/SSK2"});
        assertThat(sortCode[0], is("K1"));
        assertThat(sortCode[1], is("K1/SK1"));
        assertThat(sortCode[2], is("K1/SK1/SSK1"));
        assertThat(sortCode[3], is("K1/SK1/SSK2"));
        assertThat(sortCode[4], is("K1/SK2"));
        assertThat(sortCode[5], is("K2"));
        assertThat(sortCode[6], is("K2/SK1"));
        assertThat(sortCode[7], is("K2/SK1/SSK1"));
        assertThat(sortCode[8], is("K2/SK1/SSK2"));
    }

    /**
     * Test revers sort codes unit.
     */
    @Test
    public void whenSortReverseCodesUnit() {
        SortCodesUnits sortCodesUnits = new SortCodesUnits();
        String[] sortCode = sortCodesUnits.sortReverse(new String[]{"K1/SK1", "K1/SK2", "K1/SK1/SSK1", "K1/SK1/SSK2", "K2", "K2/SK1/SSK1", "K2/SK1/SSK2"});
        assertThat(sortCode[0], is("K2"));
        assertThat(sortCode[1], is("K2/SK1"));
        assertThat(sortCode[2], is("K2/SK1/SSK2"));
        assertThat(sortCode[3], is("K2/SK1/SSK1"));
        assertThat(sortCode[4], is("K1"));
        assertThat(sortCode[5], is("K1/SK2"));
        assertThat(sortCode[6], is("K1/SK1"));
        assertThat(sortCode[7], is("K1/SK1/SSK2"));
        assertThat(sortCode[8], is("K1/SK1/SSK1"));
    }
}