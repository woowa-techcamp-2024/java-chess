package com.woowatechcamp.utils;

public class StringUtils {
    public static final String NEW_LINE = System.getProperty("line.separator");

    private StringUtils() {
    }

    public static void appendNewLine(StringBuilder stringBuilder) {
        stringBuilder.append(NEW_LINE);
    }
}
