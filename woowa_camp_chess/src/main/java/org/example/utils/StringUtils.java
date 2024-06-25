package org.example.utils;

public class StringUtils {

    private StringUtils() {
    }

    static final String NEWLINE = System.lineSeparator();

    public static void appendNewLine(StringBuilder sb) {
        sb.append(NEWLINE);
    }
}
