package org.example.chess.pieces;

import java.util.Objects;

public class Piece {

    private final Color color;
    private final Type type;
    private final String representation;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
        this.representation = initializeRepresentation();
    }

    private String initializeRepresentation() {
        if (this.color == Color.BLACK) {
            return this.type.getRepresentation();
        }

        if (this.color == Color.WHITE) {
            return this.type.getRepresentation().toLowerCase();
        }

        return this.type.getRepresentation();
    }

    private static Piece createPiece(Color color, Type type) {
        return new Piece(color, type);
    }

    private static Piece createWhite(Type type) {
        return new Piece(Color.WHITE, type);
    }

    private static Piece createBlack(Type type) {
        return new Piece(Color.BLACK, type);
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public String getRepresentation() {
        return representation;
    }

    public boolean isWhite() {
        return this.color == Color.WHITE;
    }

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public enum Color {

        WHITE("흰색"),
        BLACK("검은색"),
        NONCOLOR("무색");

        private final String description;

        Color(String description) {
            this.description = description;
        }
    }

    public enum Type {
        PAWN("P"),
        KNIGHT("N"),
        ROOK("R"),
        BISHOP("B"),
        QUEEN("Q"),
        KING("K"),
        NO_TYPE(".");

        private final String representation;

        public String getRepresentation() {
            return this.representation;
        }

        Type(String representation) {
            this.representation = representation;
        }
    }

    public static class PieceFactory {

        public static Piece createBlank() {
            return createPiece(Color.NONCOLOR, Type.NO_TYPE);
        }

        public static Piece createWhitePawn() {
            return createWhite(Type.PAWN);
        }

        public static Piece createBlackPawn() {
            return createBlack(Type.PAWN);
        }

        public static Piece createWhiteKnight() {
            return createWhite(Type.KNIGHT);
        }

        public static Piece createBlackKnight() {
            return createBlack(Type.KNIGHT);
        }

        public static Piece createWhiteRook() {
            return createWhite(Type.ROOK);
        }

        public static Piece createBlackRook() {
            return createBlack(Type.ROOK);
        }

        public static Piece createWhiteBishop() {
            return createWhite(Type.BISHOP);
        }

        public static Piece createBlackBishop() {
            return createBlack(Type.BISHOP);
        }

        public static Piece createWhiteQueen() {
            return createWhite(Type.QUEEN);
        }

        public static Piece createBlackQueen() {
            return createBlack(Type.QUEEN);
        }

        public static Piece createWhiteKing() {
            return createWhite(Type.KING);
        }

        public static Piece createBlackKing() {
            return createBlack(Type.KING);
        }
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
        return color == piece.color && type == piece.type && Objects.equals(representation, piece.representation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, representation);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", type=" + type +
                ", representation='" + representation + '\'' +
                '}';
    }
}
