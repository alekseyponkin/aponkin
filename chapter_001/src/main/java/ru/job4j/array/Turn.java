package ru.job4j.array;

/**
* Turn.
*
* @author Ponkin Aleksey
* @version $Id$
* @since 16.07.2017
*/

public class Turn {
	/**
	* turn - переворот массива.
	* @param array - входной массив
	* @return int[] - перевернутый массив
	*/
	public int[] back(int[] array) {
		int buffer;
		int maxIndexArray = array.length - 1;
		for (int i = 0; i < array.length / 2; i++) {
			buffer = array[maxIndexArray - i];
			array[maxIndexArray - i] = array[i];
			array[i] = buffer;
			}
		return array;
	}
}