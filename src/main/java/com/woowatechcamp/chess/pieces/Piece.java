package com.woowatechcamp.chess.pieces;

import java.util.Objects;

public class Piece {
    private final Color color;
    private final Type type;
    private final Position position;

    public Piece(Color color, Type type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    private static Piece createWhite(Type type, Position position) {
        return new Piece(Color.WHITE, type, position);
    }

    private static Piece createBlack(Type type, Position position) {
        return new Piece(Color.BLACK, type, position);
    }

    public static Piece createWhitePawn(Position position) {
        return createWhite(Type.PAWN, position);
    }

    public static Piece createBlackPawn(Position position) {
        return createBlack(Type.PAWN, position);
    }

    public static Piece createWhiteRook(Position position) {
        return createWhite(Type.ROOK, position);
    }

    public static Piece createBlackRook(Position position) {
        return createBlack(Type.ROOK, position);
    }

    public static Piece createWhiteKnight(Position position) {
        return createWhite(Type.KNIGHT, position);
    }

    public static Piece createBlackKnight(Position position) {
        return createBlack(Type.KNIGHT, position);
    }

    public static Piece createWhiteBishop(Position position) {
        return createWhite(Type.BISHOP, position);
    }

    public static Piece createBlackBishop(Position position) {
        return createBlack(Type.BISHOP, position);
    }

    public static Piece createWhiteQueen(Position position) {
        return createWhite(Type.QUEEN, position);
    }

    public static Piece createBlackQueen(Position position) {
        return createBlack(Type.QUEEN, position);
    }

    public static Piece createWhiteKing(Position position) {
        return createWhite(Type.KING, position);
    }

    public static Piece createBlackKing(Position position) {
        return createBlack(Type.KING, position);
    }

    public static Piece createBlank(Position position) {
        return new Piece(Color.NONE, Type.BLANK, position);
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
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
        BLACK("black"),
        WHITE("white"),
        NONE("none");

        private final String color;

        Color(String color) {
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
