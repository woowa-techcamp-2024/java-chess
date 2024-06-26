package com.woopaca.javachess.pieces;

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

    public static Piece createBlank() {
        return new Piece(Color.NOCOLOR, Type.NO_PIECE);
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    public double getPoint() {
        return type.point;
    }

    public enum Color {

        WHITE, BLACK, NOCOLOR

    }

    public enum Type {

        PAWN('♙', '♟', 1),
        KNIGHT('♘', '♞', 2.5),
        ROOK('♖', '♜', 5),
        BISHOP('♗', '♝', 3),
        QUEEN('♕', '♛', 9),
        KING('♔', '♚', 0),
        NO_PIECE('.', '.', 0);

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
