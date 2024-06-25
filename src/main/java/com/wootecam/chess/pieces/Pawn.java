package com.wootecam.chess.pieces;

public class Pawn {

    public static final String COLOR_WHITE = "white";
    public static final String COLOR_BLACK = "black";
    public static final String WHITE_REPRESENTATION = "p";
    public static final String BLACK_REPRESENTATION = "P";

    private final String color;
    private final String representation;

    public Pawn() {
        this(COLOR_WHITE, WHITE_REPRESENTATION);
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
