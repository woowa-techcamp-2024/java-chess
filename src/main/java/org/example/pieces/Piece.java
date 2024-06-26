package org.example.pieces;

import static org.example.pieces.Piece.Color.*;

public class Piece {

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameType(Type type) {
        return this.representation == type;
    }

    public enum Color {
        WHITE, BLACK, NO_COLOR;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum Type {
        PAWN('♙'),
        ROOK('♖'),
        KNIGHT('♘'),
        BISHOP('♗'),
        QUEEN('♕'),
        KING('♔'),
        NO_PIECE('.');  // 빈 문자로 설정

        private final char representation;


        Type(char representation) {
            this.representation = representation;
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
}
