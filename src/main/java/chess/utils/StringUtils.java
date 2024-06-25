package chess.utils;

public class StringUtils {
    public static final String NEWLINE = System.lineSeparator();

    private StringUtils() {};
    public static String appendNewLine(final String line) {
        return line + NEWLINE;
    }
}
