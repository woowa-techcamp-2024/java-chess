package wootecamp.chess.pieces;

public class Pawn {
    public static final String WHITE_COLOR = "white";
    public static final String WHITE_REPRESENTATION = "p";
    public static final String BLACK_COLOR = "black";
    public static final String BLACK_REPRESENTATION = "P";

    private String color;
    private String representation;

    public Pawn() {
        this.color = WHITE_COLOR;
        this.representation = WHITE_REPRESENTATION;
    }

    public Pawn(final String color) {
        this.color = color;
        this.representation = decideRepresentation(color);
    }

    private String decideRepresentation(final String color) {
        if(color.equals(WHITE_COLOR)) {
            return WHITE_REPRESENTATION;
        }
        if(color.equals(BLACK_COLOR)) {
            return BLACK_REPRESENTATION;
        }
        throw new IllegalArgumentException("잘못된 색상 전달");
    }

    public String getColor() {
        return color;
    }

    public String getRepresentation() {
        return representation;
    }
}
