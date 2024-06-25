package utils;

public class StringUtils {
    public static final String NEWLINE = System.getProperty("line.separator");

    private StringUtils() {

    }

    public static String appendNewLine(String message) {
        return message + NEWLINE;
    }

    public static StringBuilder appendNewLine(StringBuilder message) {
        return message.append(NEWLINE);
    }
}
