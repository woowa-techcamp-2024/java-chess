package utils;

public class StringUtils {
    private StringUtils() {}//기본 생성자를 막아 인스턴스화 방지

    private static final String NEWLINE = System.lineSeparator();

    public static String appendNewLine(String str){
        return str == null ? str : str.concat(NEWLINE);
    }
}
