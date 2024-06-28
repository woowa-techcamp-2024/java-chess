package com.woopaca.javachess.pieces;

import java.util.List;
import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private final Type type;
    private final Range range;

    protected Piece(Color color, Type type, Range range) {
        this.color = color;
        this.type = type;
        this.range = range;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public char getRepresentation() {
        if (color.equals(Color.WHITE)) {
            return type.getWhiteRepresentation();
        }
        return type.getBlackRepresentation();
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public double getPoint() {
        return type.getPoint();
    }

    public boolean isBlank() {
        return type == Type.NO_PIECE;
    }

    public abstract List<Direction> getDirections();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(color);
        result = 31 * result + Objects.hashCode(type);
        return result;
    }
}
