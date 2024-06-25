package com.wootecam.chess.utils;

public class StringUtils {

    private static final String NEW_LINE = System.lineSeparator();

    private StringUtils() {
    }

    public static String appendNewLine(String input) {
        return input + NEW_LINE;
    }
}
