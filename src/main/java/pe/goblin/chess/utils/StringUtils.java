package pe.goblin.chess.utils;

public final class StringUtils {
    public static final String NEW_LINE = System.lineSeparator();

    private StringUtils() {
    }

    public static String appendNewLine(String string) {
        return string + NEW_LINE;
    }
}
