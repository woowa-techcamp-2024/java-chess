package utils;

public class StringUtils {
    public static final String NEW_LINE = System.getProperty("line.separator");

    private StringUtils() {}

    public static String appendNewLine(String s) {
        return s + NEW_LINE;
    }
}
