package ru.job4j.strategy;

/**
 * Class Triangle.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 23.09.2017
 */
public class Triangle implements Shape {
    /**
     * Draw triangle.
     * @return string in a form triangle.
     */
    @Override
    public String pic() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  ^  ");
        stringBuilder.append(System.getProperty("line.separator"));
        stringBuilder.append(" ^^^ ");
        stringBuilder.append(System.getProperty("line.separator"));
        stringBuilder.append("^^^^^");
        return stringBuilder.toString();
    }
}
