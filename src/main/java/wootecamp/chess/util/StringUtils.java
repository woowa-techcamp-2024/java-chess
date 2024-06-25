package wootecamp.chess.util;

public class StringUtils {
    public static final String NEWLINE = System.lineSeparator();

    private StringUtils() {
    }

    public static String appendNewline(final String input) {
        return input + NEWLINE;
    }
}
