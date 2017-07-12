package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test Paint.
*
* @author Ponkin Aleksey (mailto: ponkin.a.v@gmail.com)
* @version $Id$
* @since 0.1
*/

public class PaintTest {
	/**
	* Test paint piramid height 2.
	*/
	@Test
	public void whenPaintPiramidWithHeightTwo() {
		Paint paint = new Paint();
        String result = paint.piramid(2);
        final String line = System.getProperty("line.separator");
        String expected = String.format(" ^ %s^^^%s", line, line);
        assertThat(result, is(expected));
	}
	/**
	* Test paint piramid height 5.
	*/
	@Test
	public void whenPaintPiramidWithHeightFive() {
		Paint paint = new Paint();
        String result = paint.piramid(5);
        final String line = System.getProperty("line.separator");
        String expected = String.format("    ^    %s   ^^^   %s  ^^^^^  %s ^^^^^^^ %s^^^^^^^^^%s", line, line, line, line, line);
        assertThat(result, is(expected));
	}
}