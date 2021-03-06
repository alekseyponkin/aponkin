package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test Max.
*
* @author Ponkin Aleksey (mailto: ponkin.a.v@gmail.com)
* @version $Id$
* @since 0.1
*/

public class MaxTest {
	/**
	* Test First more Second.
	*/
	@Test
	public void whenFirstMoreSecond() {
		Max max = new Max();
		int result = max.max(5, 3);
		int expected = 5;
		assertThat(result, is(expected));
	}
	/**
	* Test Second more First.
	*/
	@Test
	public void whenSecondMoreFirst() {
		Max max = new Max();
		int result = max.max(2, 7);
		int expected = 7;
		assertThat(result, is(expected));
	}
	/**
	* Test First more Second and Third.
	*/
	@Test
	public void whenFirstMoreSecondAndThird() {
		Max max = new Max();
		int result = max.max(5, 3, 1);
		int expected = 5;
		assertThat(result, is(expected));
	}
	/**
	* Test Second more First and Third.
	*/
	@Test
	public void whenSecondMoreFirstAndThird() {
		Max max = new Max();
		int result = max.max(2, 7, 3);
		int expected = 7;
		assertThat(result, is(expected));
	}
	/**
	* Test Third more First and Second.
	*/
	@Test
	public void whenThirdMoreFirstAndSecond() {
		Max max = new Max();
		int result = max.max(2, 7, 9);
		int expected = 9;
		assertThat(result, is(expected));
	}
}