package utils;

public class StringUtils {

	private StringUtils() {
	}

	public static String appendNewLine() {
		return System.lineSeparator();
	}

	public static boolean equalsIgnoreCase(String str1, String str2) {
		if (str1 == null && str2 == null) {
			return true;
		}
		if (str1 == null || str2 == null) {
			return false;
		}
		return str1.equalsIgnoreCase(str2);
	}
}
