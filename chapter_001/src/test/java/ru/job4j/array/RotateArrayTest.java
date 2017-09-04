package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test RotateArray.
*
* @author Ponkin Aleksey (mailto: ponkin.a.v@gmail.com)
* @version $Id$
* @since 0.1
*/

public class RotateArrayTest {
	/**
	* Test turn array 2x2.
	*/
	@Test
	public void whenTurnArrayTwoOnTwo() {
		RotateArray array = new RotateArray();
		int[][] result = {{1, 3}, {2, 4}};
        int[][] resultArray = array.rotate(result);
        int[][] expectArray = {{3, 4}, {1, 2}};
        assertThat(resultArray, is(expectArray));
	}
	/**
	* Test turn array 3x3.
	*/
	@Test
	public void whenTurnArrayThreeOnThree() {
		RotateArray array = new RotateArray();
		int[][] result = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] resultArray = array.rotate(result);
        int[][] expectArray = {{7, 8, 9}, {4, 5, 6}, {1, 2, 3}};
        assertThat(resultArray, is(expectArray));
	}
}