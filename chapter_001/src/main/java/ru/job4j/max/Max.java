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
	* Max - определение максимума.
	* @param first - первый аргумент
	* @param second - второй аргумент
	* @return максимальное число
	*/
	public int max(int first, int second)	{
		return first > second ? first : second;
	}
}