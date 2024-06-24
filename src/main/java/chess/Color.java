package chess;

public final class Color {
    public static final String WHITE = "white";
    public static final String BLACK = "black";

    public static String getColor(final String color) {
        if (color.equals(WHITE)) return WHITE;
        return BLACK;
    }
}
