package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Ponkin Aleksey (mailto: ponkin.a.v@gmail.com)
* @version $Id$
* @since 0.1
*/

public class CalculatorTest {
	/**
	* Test add.
	*/
	@Test
	public void whenAddOnePlusOneThenTwo() {
		Calculator calc = new Calculator();
		calc.add(1D, 1D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}
	/**
	* Test substruct.
	*/
	@Test
	public void whenSubstructOnePlusOneThenTwo() {
		Calculator calc = new Calculator();
		calc.substruct(3D, 1D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}
	/**
	* Test div.
	*/
	@Test
	public void whenDivOnePlusOneThenTwo() {
		Calculator calc = new Calculator();
		calc.div(3D, 3D);
		double result = calc.getResult();
		double expected = 1D;
		assertThat(result, is(expected));
	}
	/**
	* Test multiple.
	*/
	@Test
	public void whenMultipleOnePlusOneThenTwo() {
		Calculator calc = new Calculator();
		calc.multiple(3D, 3D);
		double result = calc.getResult();
		double expected = 9D;
		assertThat(result, is(expected));
	}
}