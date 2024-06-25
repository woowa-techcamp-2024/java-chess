package chess.utils;

public class StringUtils {
    public static final char BLANK = '.';
    public static final String NEWLINE = System.lineSeparator();

    public static void appendNewLine(StringBuilder sb) {
        sb.append(NEWLINE);
    }
}
