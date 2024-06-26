package org.example.pieces;

import static org.example.pieces.Piece.Color.*;

import java.util.Objects;

public class Piece {

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameType(Type type) {
        return this.representation == type;
    }

    public double getPoint() {
        return representation.point;
    }

    public enum Color {
        WHITE, BLACK, NO_COLOR;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum Type {
        PAWN('♙', 1.0),
        ROOK('♖', 5.0),
        KNIGHT('♘', 2.5),
        BISHOP('♗', 3.0),
        QUEEN('♕', 9.0),
        KING('♔', 0.0),
        NO_PIECE('.', 0.0);  // 빈 문자로 설정

        private final char representation;
        private final double point;


        Type(char representation, double point) {
            this.representation = representation;
            this.point = point;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            final int offsetNumber = 6;
            if (this == NO_PIECE) {
                return representation;
            }

            return (char) (representation + offsetNumber);
        }
    }

    private final Color color;
    private final Type representation;

    private Piece(Color color, Type representation) {
        this.color = color;
        this.representation = representation;
    }

    public static Piece createWhitePawn() {
        return new Piece(WHITE, Type.PAWN);
    }

    public static Piece createBlackPawn() {
        return new Piece(BLACK, Type.PAWN);
    }

    public static Piece createWhiteKnight() {
        return new Piece(WHITE, Type.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return new Piece(BLACK, Type.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return new Piece(WHITE, Type.ROOK);
    }

    public static Piece createBlackRook() {
        return new Piece(BLACK, Type.ROOK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(WHITE, Type.BISHOP);
    }

    public static Piece createBlackBishop() {
        return new Piece(BLACK, Type.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return new Piece(WHITE, Type.QUEEN);
    }

    public static Piece createBlackQueen() {
        return new Piece(BLACK, Type.QUEEN);
    }

    public static Piece createWhiteKing() {
        return new Piece(WHITE, Type.KING);
    }

    public static Piece createBlackKing() {
        return new Piece(BLACK, Type.KING);
    }

    public static Piece createNoColorPiece() {
        return new Piece(NO_COLOR, Type.NO_PIECE);
    }

    public Color getColor() {
        return color;
    }

    public char getRepresentation() {
        return isWhite() ?
            representation.getWhiteRepresentation() : representation.getBlackRepresentation();
    }

    public boolean isWhite() {
        return color.equals(WHITE);
    }

    public boolean isBlack() {
        return color.equals(BLACK);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Piece piece)) {
            return false;
        }
        return color == piece.color
            && representation == piece.representation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, representation);
    }
}
