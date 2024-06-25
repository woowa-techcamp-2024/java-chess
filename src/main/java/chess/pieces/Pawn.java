package chess.pieces;

public class Pawn {
    String color;
    char representation;

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final char BLACK_REPRESENTATION = 'P';
    public static final char WHITE_REPRESENTATION = 'p';

    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }

    public Pawn(String color) {
        this.color = color;
    }

    public Pawn(String color, char representation) {
        this.color = color;
        this.representation = representation;
    }

    public Pawn() {
        this.color = WHITE_COLOR;
        this.representation = WHITE_REPRESENTATION;
    }
}
