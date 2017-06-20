package ru.job4j.condition;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
* Test Triangle.
*
* @author Ponkin Aleksey (mailto: ponkin.a.v@gmail.com)
* @version $Id$
* @since 0.1
*/
public class TriangleTest {
	/**
	* Test вычисления площади треугольника.
	*/
	@Test
	public void whenTriangle() {
		Triangle triangle = new Triangle(new Point(-2, -3), new Point(-3, 4), new Point(4, 5));
		double result = triangle.area();
		double expected = 25;
		assertThat(result, closeTo(expected, 0.01));
	}
	/**
	* Test точка А равна B.
	*/
	@Test
	public void whenPointAEqualsB() {
		Triangle triangle = new Triangle(new Point(-2, -3), new Point(-2, -3), new Point(4, 5));
		double result = triangle.area();
		double expected = 0;
		assertThat(result, closeTo(expected, 0.01));
	}
	/**
	* Test точка А равна B.
	*/
	@Test
	public void whenPointBEqualsC() {
		Triangle triangle = new Triangle(new Point(-2, -3), new Point(4, 5), new Point(4, 5));
		double result = triangle.area();
		double expected = 0;
		assertThat(result, closeTo(expected, 0.01));
	}
	/**
	* Test точка А равна C.
	*/
	@Test
	public void whenPointAEqualsC() {
		Triangle triangle = new Triangle(new Point(-3, -3), new Point(4, 5), new Point(-3, -3));
		double result = triangle.area();
		double expected = 0;
		assertThat(result, closeTo(expected, 0.01));
	}
}