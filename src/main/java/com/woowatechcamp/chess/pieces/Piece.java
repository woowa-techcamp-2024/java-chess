package com.woowatechcamp.chess.pieces;

public class Piece {
    private final Type type;
    private final Color color;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public static Piece createWhitePawn() {
        return new Piece(Color.WHITE, Type.WHITE_PAWN);
    }

    public static Piece createBlackPawn() {
        return new Piece(Color.BLACK, Type.BLACK_PAWN);
    }

    public static Piece createWhiteRook() {
        return new Piece(Color.WHITE, Type.WHITE_ROOK);
    }

    public static Piece createBlackRook() {
        return new Piece(Color.BLACK, Type.BLACK_ROOK);
    }

    public static Piece createWhiteKnight() {
        return new Piece(Color.WHITE, Type.WHITE_KNIGHT);
    }

    public static Piece createBlackKnight() {
        return new Piece(Color.BLACK, Type.BLACK_KNIGHT);
    }

    public static Piece createWhiteBishop() {
        return new Piece(Color.WHITE, Type.WHITE_BISHOP);
    }

    public static Piece createBlackBishop() {
        return new Piece(Color.BLACK, Type.BLACK_BISHOP);
    }

    public static Piece createWhiteQueen() {
        return new Piece(Color.WHITE, Type.WHITE_QUEEN);
    }

    public static Piece createBlackQueen() {
        return new Piece(Color.BLACK, Type.BLACK_QUEEN);
    }

    public static Piece createWhiteKing() {
        return new Piece(Color.WHITE, Type.WHITE_KING);
    }

    public static Piece createBlackKing() {
        return new Piece(Color.BLACK, Type.BLACK_KING);
    }

    public Color getColor() {
        return color;
    }

    public char getRepresentation() {
        return type.getSymbol();
    }

    @Override
    public String toString() {
        return String.valueOf(getRepresentation());
    }
}
