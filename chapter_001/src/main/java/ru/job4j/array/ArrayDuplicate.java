package ru.job4j.array;

import java.util.Arrays;

/**
* ArrayDuplicate.
*
* @author Ponkin Aleksey
* @version $Id$
* @since 5.09.2017
*/

public class ArrayDuplicate {
	/**
	* ArrayDuplicate - удаление дубликатов в массиве.
	* @param array - входной массив.
	* @return String[] - масив уникальных значений.
	*/
	public String[] remove(String[] array) {
		int countChange = 0;
		for (int i = 0; i < array.length - countChange; i++) {
			for (int j = i + 1; j < array.length - 1 - countChange; j++) {
				String temp;
				if (array[i].equals(array[j])) {
					while (array[j].equals(array[array.length - 1 - countChange])) {
						countChange++;
					}
					temp = array[array.length - 1 - countChange];
					array[array.length - 1 - countChange] = array[j];
					array[j] = temp;
					countChange++;
				}
			}
		}
		return Arrays.copyOf(array, array.length - countChange);
	}
}