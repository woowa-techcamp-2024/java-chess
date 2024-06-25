package com.wootecam.chess.pieces;

public class Piece {

    private final Color color;
    private final Type type;

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public static Piece createBlack(final Type type) {
        return new Piece(Color.BLACK, type);
    }

    public static Piece createWhite(final Type type) {
        return new Piece(Color.WHITE, type);
    }

    public static Piece createBlank() {
        return new Piece(Color.NO_COLOR, Type.NO_PIECE);
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public String getRepresentation() {
        return type.findRepresentation(color);
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }
}
