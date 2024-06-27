package com.woopaca.javachess.pieces;

import com.woopaca.javachess.chess.Position;

import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private final Type type;
    private Position position;

    protected Piece(final Color color, final Type type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
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

    public void updatePosition(Position newPosition) {
        this.position = newPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type && Objects.equals(position, piece.position);
    }

}
