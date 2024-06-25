package org.example.pieces;

public class Pawn {

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    public static final String WHITE_REPRESENTATION = "\u2659";
    public static final String BLACK_REPRESENTATION = "\u265F";

    private final String color;
    private String representation;

    public Pawn(String color) {
        this.color = color;
        this.representation =
            color.equals(WHITE_COLOR) ? WHITE_REPRESENTATION : BLACK_REPRESENTATION;
    }

    public Pawn() {
        this(WHITE_COLOR);
    }

    public String getColor() {
        return color;
    }

    public String getRepresentation() {
        return representation;
    }

}
