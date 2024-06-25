package org.example.utils;

public class StringUtils {

    private static final String NEWLINE = System.getProperty("line.separator");

    public static String appendNewLine(String line) {
        return line + NEWLINE;
    }

    private StringUtils() {

    }
}

