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
    }

    public Pawn() {
        this.color = Pawn.WHITE_COLOR;
    }

    public String getColor() {
        return this.color;
    }

    public char getRepresentation() {return this.representation;}

}