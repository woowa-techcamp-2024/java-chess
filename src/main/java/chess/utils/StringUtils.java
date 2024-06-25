package chess.utils;

public class StringUtils {

    public static final String NEWLINE = System.getProperty("line.separator");

    public static StringBuilder appendNewLine(StringBuilder sb) {
        sb.append(NEWLINE);
        return sb;
    }

    public static String appendNewLine(String str) {
        return str + NEWLINE;
    }

    private StringUtils() {}
}
