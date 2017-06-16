package ru.job4j.calculator;

/**
* Calculator.
*
* @author Ponkin Aleksey
* @version $Id$
* @since 13.06.2017
*/

public class Calculator {
	/**
	* Поле резулатата.
	*/
	private double result;
	/**
	* Add -  сложение.
	* @param first - первый аргумент
	* @param second - второй аргумент
	*/
	public void add(double first, double second) {
		this.result = first + second;
	}
	/**
	* substruct - вычитание.
	* @param first - первый аргумент
	* @param second - второй аргумент
	*/
	public void substruct(double first, double second) {
		this.result = first - second;
	}
	/**
	* div - деление.
	* @param first - первый аргумент
	* @param second - второй аргумент
	*/
	public void div(double first, double second) {
		this.result = first / second;
	}
	/**
	* multiple - умножение.
	* @param first - первый аргумент
	* @param second - второй аргумент
	*/
	public void multiple(double first, double second) {
		this.result = first * second;
	}
	/**
	* Возвращает результат.
	* @return возвращаем результат вычислений
	*/
	public double getResult() {
		return this.result;
	}
}