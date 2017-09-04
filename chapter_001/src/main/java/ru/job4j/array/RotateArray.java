package ru.job4j.array;

/**
* RotateArray.
*
* @author Ponkin Aleksey
* @version $Id$
* @since 4.09.2017
*/

public class RotateArray {
	/**
	* RotateArray - переворот массива.
	* @param array - входной двумерный массив
	* @return int[][] - перевернутый двумерный массив
	*/
	public int[][] rotate(int[][] array) {
		int maxIndexArray = array.length;
		for (int i = 0; i < maxIndexArray / 2; i++) {
			for (int j = i; j < maxIndexArray - 1 - i; j++) {
				int temp = array[i][j];
				array[i][j] = array[j][maxIndexArray - 1 - i];
				array[j][maxIndexArray - 1 - i] = array[maxIndexArray - 1 - i][maxIndexArray - 1 - j];
				array[maxIndexArray - 1 - i][maxIndexArray - 1 - j] = array[maxIndexArray - 1 - j][i];
				array[maxIndexArray - 1 - j][i] = temp;
			}
		}
		return array;
	}
}