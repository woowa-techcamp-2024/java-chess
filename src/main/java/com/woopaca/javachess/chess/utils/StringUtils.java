package com.woopaca.javachess.chess.utils;

public class StringUtils {

    public static final String NEWLINE = System.lineSeparator();

    private StringUtils() {
    }

    public static String appendNewLine(String subject) {
        return subject.concat(NEWLINE);
    }

}
