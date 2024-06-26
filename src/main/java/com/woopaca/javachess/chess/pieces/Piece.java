package com.woopaca.javachess.chess.pieces;

public class Piece {

    private final Color color;
    private final Type type;

    private Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
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

    public enum Color {

        WHITE, BLACK, NOCOLOR

    }

    public enum Type {

        PAWN('♙', '♟'),
        KNIGHT('♘', '♞'),
        ROOK('♖', '♜'),
        BISHOP('♗', '♝'),
        QUEEN('♕', '♛'),
        KING('♔', '♚');

        private final char whiteRepresentation;
        private final char blackRepresentation;

        Type(char whiteRepresentation, char blackRepresentation) {
            this.whiteRepresentation = whiteRepresentation;
            this.blackRepresentation = blackRepresentation;
        }

        public char getWhiteRepresentation() {
            return whiteRepresentation;
        }

        public char getBlackRepresentation() {
            return blackRepresentation;
        }

    }

}
