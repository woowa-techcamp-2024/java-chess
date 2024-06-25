package com.woopaca.javachess.chess.utils;

public class StringUtils {

    public static final String NEWLINE = System.lineSeparator();

    private StringUtils() {
    }

    public static StringBuilder appendNewLine(StringBuilder stringBuilder, String subject) {
        return stringBuilder.append(subject)
                .append(NEWLINE);
    }

}
