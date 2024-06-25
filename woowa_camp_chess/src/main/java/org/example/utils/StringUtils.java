package org.example.utils;

public class StringUtils {

    private StringUtils() {
    }

    static final String NEWLINE = System.lineSeparator();

    public static String appendNewLine(String str) {
        return str+NEWLINE;
    }
}
