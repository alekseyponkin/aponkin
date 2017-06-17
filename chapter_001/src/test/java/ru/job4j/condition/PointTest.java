package ru.job4j.point;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test Point.
*
* @author Ponkin Aleksey (mailto: ponkin.a.v@gmail.com)
* @version $Id$
* @since 0.1
*/
public class PointTest {
	/**
	* Test точка находится на этой функции.
	*/
	@Test
	public void whenPointIs() {
		Point point = new Point(2, 4);
		boolean result = point.is(1, 2);
		boolean expected = true;
		assertThat(result, is(expected));
	}
	/**
	* Test точка не находится на этой функции.
	*/
	@Test
	public void whenPointNotIs() {
		Point point = new Point(2, 9);
		boolean result = point.is(2, 2);
		boolean expected = false;
		assertThat(result, is(expected));
	}

}