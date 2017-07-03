package ru.job4j.loop;

/**
* Counter.
*
* @author Ponkin Aleksey
* @version $Id$
* @since 03.07.2017
*/

public class Counter {
	/**
	* Add - сложение четных чисел.
	* @param start - начальное число
	* @param finish - финальное число
	* @return int
	*/
	public int add(int start, int finish) {
		int result = 0;
		for (int i = start; i <= finish; i++) {
			if (i % 2 == 0) {
				result = result + i;
			}
		}
		return result;
	}
}