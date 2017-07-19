package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test Bubble sort.
*
* @author Ponkin Aleksey (mailto: ponkin.a.v@gmail.com)
* @version $Id$
* @since 0.1
*/

public class BubbleSortTest {
	/**
	* Test sort array eight elements.
	*/
	@Test
	public void whenArrayBubbleSortEightElements() {
		BubbleSort arrayTest = new BubbleSort();
		int[] result = {67, -44, 9, 333, 111, -7, 1, 99};
        int[] resultArray = arrayTest.sort(result);
        int[] expectArray = {-44, -7, 1, 9, 67, 99, 111, 333};
        assertThat(resultArray, is(expectArray));
	}
}