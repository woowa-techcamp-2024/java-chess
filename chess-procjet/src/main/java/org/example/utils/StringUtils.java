package org.example.utils;

public class StringUtils {

    private static final String NEWLINE = System.getProperty("line.separator");

    public static void appendNewLine(StringBuilder sb) {
        sb.append(NEWLINE);
    }

    private StringUtils() {

    }
}

