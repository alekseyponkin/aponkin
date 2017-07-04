package ru.job4j.loop;

/**
* Factorial.
*
* @author Ponkin Aleksey
* @version $Id$
* @since 04.07.2017
*/

public class Factorial {
	/**
	* calc - вычисление факториала числа n.
	* @param n - число
	* @return int
	*/
	public int calc(int n) {
		int result = 1;
		if (n == 0) {
				return 1;
		}
		for (int i = 1; i <= n; i++) {
			result = result * i;
		}
		return result;
	}
}