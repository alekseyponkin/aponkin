package ru.job4j.max;

/**
* Max.
*
* @author Ponkin Aleksey
* @version $Id$
* @since 08.06.2017
*/

public class Max {
	/**
	* Max - определение максимума из 2 чесел.
	* @param first - первый аргумент
	* @param second - второй аргумент
	* @return максимальное число
	*/
	public int max(int first, int second) {
		return first > second ? first : second;
	}
	/**
	* Max - определение максимума из 3 чисел.
	* @param first - первый аргумент
	* @param second - второй аргумент
	* @param third - третий аргумент
	* @return максимальное число
	*/
	public int max(int first, int second, int third) {
		return max(first, max(second, third));
	}
}