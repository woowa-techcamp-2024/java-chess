package com.seong.chess.utils;

public final class StringUtils {

    public static final String NEW_LINE = System.lineSeparator();

    private StringUtils() {
    }

    public static String appendNewLine(String str) {
        return str + NEW_LINE;
    }
}
