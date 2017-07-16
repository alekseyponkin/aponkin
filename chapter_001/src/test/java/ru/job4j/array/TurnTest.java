package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test Turn.
*
* @author Ponkin Aleksey (mailto: ponkin.a.v@gmail.com)
* @version $Id$
* @since 0.1
*/

public class TurnTest {
	/**
	* Test turn array odd.
	*/
	@Test
	public void whenTurnArrayLenghtOdd() {
		Turn array = new Turn();
		int[] result = {5, 4, 3, 2, 1};
        int[] resultArray = array.back(result);
        int[] expectArray = {1, 2, 3, 4, 5};
        assertThat(resultArray, is(expectArray));
	}
	/**
	* Test turn array even.
	*/
	@Test
	public void whenTurnArrayLenghtEven() {
		Turn array = new Turn();
		int[] result = {4, 8, 2, 0};
        int[] resultArray = array.back(result);
        int[] expectArray = {0, 2, 8, 4};
        assertThat(resultArray, is(expectArray));
	}
}