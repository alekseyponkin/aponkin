package ru.job4j.array;

/**
* Bubble Sort.
*
* @author Ponkin Aleksey
* @version $Id$
* @since 17.07.2017
*/

public class BubbleSort {
	/**
	* sort - сортировка массива пызурьком.
	* @param array - входной массив
	* @return int[] - отсортированный массив
	*/
	public int[] sort(int[] array) {
		for (int i = array.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (array[j] > array[j + 1]) {
					int buffer = array[j];
					array[j] = array[j + 1];
					array[j + 1] = buffer;
				}
			}
		}
		return array;
	}
}