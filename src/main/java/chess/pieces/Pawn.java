package chess.pieces;

public class Pawn {
    private final String color;
    private final char representation;

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final char WHITE_REPRESENTATION = 'p';
    public static final char BLACK_REPRESENTATION = 'P';

    public Pawn() {
        this.color = WHITE_COLOR;
        this.representation = WHITE_REPRESENTATION;
    }

    public Pawn(final String color, final char representation) {
        this.color = color;
        this.representation = representation;
    }

    public String getColor() {
        return this.color;
    }

    public char getRepresentation() {
        return this.representation;
    }
}