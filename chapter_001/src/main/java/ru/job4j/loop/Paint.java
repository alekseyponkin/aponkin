package ru.job4j.loop;

/**
* Paint.
*
* @author Ponkin Aleksey
* @version $Id$
* @since 10.07.2017
*/

public class Paint {
	/**
	* piramid - рисование пирамиды.
	* @param h - высота пирамиды
	* @return String
	*/
	public String piramid(int h) {
		StringBuilder strBuider = new StringBuilder();
		int maxLine = 1;
		for (int i = 1; i < h; i++) {
			maxLine = maxLine + 2;
		}
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < maxLine / 2 - i; j++) {
				strBuider.append(" ");
			}
			for (int j = 0; j < i; j++) {
				strBuider.append("^");
			}
			strBuider.append("^");
			for (int j = 0; j < i; j++) {
				strBuider.append("^");
			}
			for (int j = 0; j < maxLine / 2 - i; j++) {
				strBuider.append(" ");
			}
			strBuider.append(System.getProperty("line.separator"));
		}
		return strBuider.toString();
	}
}