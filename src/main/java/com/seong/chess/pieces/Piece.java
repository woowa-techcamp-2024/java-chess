package com.seong.chess.pieces;

public class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p'),
        KNIGHT('n'),
        BISHOP('b'),
        ROOK('r'),
        QUEEN('q'),
        KING('k'),
        NO_PIECE('.');

        private final char representation;

        Type(char representation) {
            this.representation = representation;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }
    }

    private final Type type;

    private final Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public static Piece createBlank() {
        return new Piece(Type.NO_PIECE, Color.NOCOLOR);
    }

    public static Piece createWhitePawn() {
        return createWhite(Type.PAWN);
    }

    public static Piece createBlackPawn() {
        return createBlack(Type.PAWN);
    }

    public static Piece createWhiteKing() {
        return createWhite(Type.KING);
    }

    public static Piece createBlackKing() {
        return createBlack(Type.KING);
    }

    public static Piece createWhiteQueen() {
        return createWhite(Type.QUEEN);
    }

    public static Piece createBlackQueen() {
        return createBlack(Type.QUEEN);
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

    public static Piece createWhiteKnight() {
        return createWhite(Type.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return createBlack(Type.KNIGHT);
    }

    private static Piece createWhite(Type type) {
        return new Piece(type, Color.WHITE);
    }

    private static Piece createBlack(Type type) {
        return new Piece(type, Color.BLACK);
    }

    public char getRepresentation() {
        return isWhite() ? type.getWhiteRepresentation() : type.getBlackRepresentation();
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isNotBlank() {
        return !type.equals(Type.NO_PIECE);
    }

    public String getColor() {
        return color.toString();
    }

    public Type getType() {
        return type;
    }
}
