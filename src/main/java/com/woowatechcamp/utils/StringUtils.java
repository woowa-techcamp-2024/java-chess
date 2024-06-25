package com.woowatechcamp.utils;

public class StringUtils {
    public static final String NEW_LINE = System.getProperty("line.separator");

    private StringUtils() {
    }

    public static String appendNewLine(String string) {
        return string + NEW_LINE;
    }
}
