package ru.job4j.point;

/**
* Point.
*
* @author Ponkin Aleksey
* @version $Id$
* @since 17.06.2017
*/

public class Point {
	/** Поле x. */
	private int x;
	/** Поле y. */
	private int y;
	/**
	* Конструктор.
	* @param x Координата X
	* @param y Координата Y
	*/
	public Point(int x, int y)	{
		this.x = x;
		this.y = y;
	}
	/**
	* Возвращает точку x.
	* @return x
	*/
	public int getX() {
		return this.x;
	}
	/**
	* Возвращает точку y.
	* @return y
	*/
	public int getY() {
		return this.y;
	}
	/**
	* is - Определение находится ли точка на этой функции.
	* @return boolean
	* @param a Параметр функции
	* @param b Параметр функции
	*/
	public boolean is(int a, int b) {
		return this.y == (a * this.x) + b;
	}
}