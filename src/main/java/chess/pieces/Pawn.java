package chess.pieces;

import java.util.Arrays;

public class Pawn {

    public static final String WHITE_COLOR = "white";

    public static final char WHITE_REPRESENTATION = 'p';

    public static final String BLACK_COLOR = "black";

    public static final char BLACK_REPRESENTATION = 'P';

    private static final String DEFAULT_COLOR = WHITE_COLOR;

    private static final char DEFAULT_REPRESENTATION = WHITE_REPRESENTATION;

    private final Color color;

    public enum Color {
        BLACK(BLACK_COLOR, BLACK_REPRESENTATION),
        WHITE(WHITE_COLOR, WHITE_REPRESENTATION);

        private final String lowerName;
        private final char representation;

        Color(final String lowerName, final char representation) {
            this.lowerName = lowerName;
            this.representation = representation;
        }

        public static Color fromLowerName(String lowerName) {
            return Arrays.stream(Color.values())
                    .filter(color -> color.lowerName.equals(lowerName))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 컬러 입니다."));
        }

    }

    public Pawn(Color color) {
        this.color = color;
    }

    public Pawn(String color) {
        this.color = Color.fromLowerName(color);
    }

    public Pawn() {
        this.color = Color.fromLowerName(DEFAULT_COLOR);
    }

    public String getColor() {
        return color.lowerName;
    }

    public char getRepresentation() {
        return color.representation;
    }
}