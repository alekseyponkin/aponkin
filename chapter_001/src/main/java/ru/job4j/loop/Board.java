package ru.job4j.loop;

/**
* Board.
*
* @author Ponkin Aleksey
* @version $Id$
* @since 04.07.2017
*/

public class Board {
	/**
	* paint - формирование доски.
	* @param width - ширина доски
	* @param height - высота строки
	* @return String
	*/
	public String paint(int width, int height) {
		StringBuilder strBuider = new StringBuilder();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if ((i + j) % 2 == 0) {
					strBuider.append("x");
				} else {
					strBuider.append(" ");
				}
			}
			strBuider.append(System.getProperty("line.separator"));
		}
		return strBuider.toString();
	}
}