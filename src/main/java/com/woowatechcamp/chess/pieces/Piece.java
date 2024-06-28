package com.woowatechcamp.chess.pieces;

import com.woowatechcamp.chess.game.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final Color color;
    private final Type type;
    private Position position;

    public Piece(Color color, Type type, Position position) {
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

    public void move(Position position, Board board) {
        validateMove(position, board);
        this.position = position;
    }

    abstract protected void validateMove(Position position, Board board);

    public boolean canMoveTo(Position position, Board board) {
        try {
            validateMove(position, board);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public void undoMove(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public char getRepresentation() {
        if (color == Color.WHITE) {
            return type.getWhiteRepresentation();
        }
        return type.getBlackRepresentation();
    }

    public boolean isNotBlank() {
        return type != Type.BLANK;
    }

    public boolean isSameTypeAndColor(Type type, Color color) {
        return this.type == type && this.color == color;
    }

    public List<Position> getPossibleMoves(Board board) {
        List<Position> possibleMoves = new ArrayList<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                try {
                    Position position = new Position(x, y);
                    validateMove(position, board);
                    possibleMoves.add(position);
                } catch (IllegalArgumentException ignore) {}
            }
        }
        return possibleMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.getColor() && type == piece.getType() && Objects.equals(position, piece.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, position);
    }

    @Override
    public String toString() {
        return String.valueOf(getRepresentation());
    }

    public enum Color {
        BLACK("Black"),
        WHITE("White"),
        NONE("None");

        private final String color;

        private Color(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

    public enum Type {
        PAWN ('♙', '♟', 1.0),
        KNIGHT('♘', '♞', 2.5),
        BISHOP('♗', '♝', 3.0),
        ROOK('♖', '♜', 5.0),
        QUEEN('♕', '♛', 9.0),
        KING('♔', '♚', 0.0),
        BLANK('.', '.', 0.0);

        private final char whiteRepresentation;
        private final char blackRepresentation;
        private final double point;

        Type(char whiteRepresentation, char blackRepresentation, double point) {
            this.whiteRepresentation = whiteRepresentation;
            this.blackRepresentation = blackRepresentation;
            this.point = point;
        }

        public char getWhiteRepresentation() {
            return whiteRepresentation;
        }

        public char getBlackRepresentation() {
            return blackRepresentation;
        }

        public double getPoint() {
            return point;
        }
    }
}
