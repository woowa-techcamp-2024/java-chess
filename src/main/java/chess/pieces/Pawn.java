package chess.pieces;

public class Pawn {

    public static final String BLACK_COLOR = "black";
    public static final String WHITE_COLOR = "white";
    public static final char WHITE_REPRESENTATION = 'p';
    public static final char BLACK_REPRESENTATION = 'P';


    private String color;
    private Character representation;

    public Pawn(String color, Character representation) {
        this.color = color;
        this.representation = representation;
    }

    public Pawn(String color) {
        this.color = color;
        if (color.equals(BLACK_COLOR)) {
            this.representation = WHITE_REPRESENTATION;
        } else if (color.equals(WHITE_COLOR)) {
            this.representation = BLACK_REPRESENTATION;
        }
    }

    public Pawn() {
        this.color = Pawn.WHITE_COLOR;
        this.representation = Pawn.WHITE_REPRESENTATION;
    }

    public String getColor() {
        return this.color;
    }

    public char getRepresentation() {
        return this.representation;
    }

}