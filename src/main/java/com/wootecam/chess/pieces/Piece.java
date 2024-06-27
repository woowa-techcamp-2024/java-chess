package com.wootecam.chess.pieces;

import java.util.Objects;

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

    public boolean isBlank() {
        return type == Type.NO_PIECE && color == Color.NO_COLOR;
    }

    public boolean isSameColorAndType(final Color color, final Type type) {
        return color == this.color && type == this.type;
    }

    public boolean isApplicablePiece(final Color color) {
        return this.color == color && type.isPiece();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(color);
        result = 31 * result + Objects.hashCode(type);
        return result;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", type=" + type +
                '}';
    }
}
