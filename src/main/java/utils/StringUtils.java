package utils;

public class StringUtils {
    private static final String NEWLINE = System.lineSeparator();

    private StringUtils() {
    }

    public static String appendNewLine(String str) {

        return str + NEWLINE;
    }

    public static String replaceNewLine(String str) {
        return str.replaceAll("\r", NEWLINE);
    }
}
