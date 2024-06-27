package com.wootecam.chess.utils;

public final class StringUtils {
    public static final String NEW_LINE = System.lineSeparator();
    public static final String BLANK = " ";

    private StringUtils() {
    }

    public static void appendNewLine(StringBuilder sb) {
        sb.append(NEW_LINE);
    }
}
