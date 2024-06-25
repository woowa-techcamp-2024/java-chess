package chess.pieces;

public class Pawn {
    public static String WHITE_COLOR = "white";
    public static String BLACK_COLOR = "black";

    public static String WHITE_REPRESENTATION = "p";
    public static String BLACK_REPRESENTATION = "P";

    private String color;
    private String representation;

    public Pawn() {
        this.color = "white";
        this.representation = WHITE_REPRESENTATION;
    }

    public Pawn(String color, String representation) {
        this.color = color;
        this.representation = representation;
    }

    public String getColor() {
        return color;
    }

    public String getRepresentation() {
        return representation;
    }
}
