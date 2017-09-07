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
		int maxLine = h + h - 1;
		int centerLine = maxLine / 2;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < maxLine; j++) {
				if ((j >= centerLine - i) && (j <= centerLine + i)) {
				strBuider.append("^");
				} else {
				strBuider.append(" ");
				}
			}
			strBuider.append(System.getProperty("line.separator"));
		}
		return strBuider.toString();
	}
}