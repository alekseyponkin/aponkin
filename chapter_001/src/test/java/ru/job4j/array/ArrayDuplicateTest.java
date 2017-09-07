package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test ArrayDuplicate.
*
* @author Ponkin Aleksey (mailto: ponkin.a.v@gmail.com)
* @version $Id$
* @since 0.1
*/

public class ArrayDuplicateTest {
	/**
	* Test удаления дубликатов в массиве.
	*/
	@Test
	public void whenArrayRemoveDuplicate() {
		ArrayDuplicate array = new ArrayDuplicate();
		String[] result = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] resultArray = array.remove(result);
        String[] expectArray = {"Привет", "Мир", "Супер"};
        assertThat(resultArray, is(expectArray));
	}
}