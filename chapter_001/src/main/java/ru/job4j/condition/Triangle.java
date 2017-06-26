package ru.job4j.condition;

/**
* Triangle.
*
* @author Ponkin Aleksey
* @version $Id$
* @since 19.06.2017
*/

public class Triangle {
	/** Полe Точка а. */
	private Point a;
	/** Полe Точка b. */
	private Point b;
	/** Полe Точка c. */
	private Point c;

	/**
	* Конструктор.
	* @param a Точка a
	* @param b Точка b
	* @param c Точка c
	*/
	public Triangle(Point a, Point b, Point c)	{
		this.a = a;
		this.b = b;
		this.c = c;
	}
	/**
	* Вычисление площади треугольника.
	* @return area
	*/
	public double area() {
		if (this.a.getX() == this.b.getX()) {
			if (this.a.getY() == this.b.getY()) {
				return 0;
			}
		}
		if (this.a.getX() == this.c.getX()) {
			if (this.a.getY() == this.c.getY()) {
				return 0;
			}
		}
		if (this.b.getX() == this.c.getX()) {
			if (this.b.getY() == this.c.getY()) {
				return 0;
			}
		}
		if (0 == Math.abs(((this.c.getX() - this.a.getX()) / (this.b.getX() - this.a.getX())) - ((this.c.getY() - this.a.getY()) / (this.b.getY() - this.a.getY())))) {
				return 0;
		}
		return Math.abs(1d / 2 * ((this.b.getX() - this.a.getX()) * (this.c.getY() - this.a.getY()) - (this.c.getX() - this.a.getX()) * (this.b.getY() - this.a.getY())));
	}
}