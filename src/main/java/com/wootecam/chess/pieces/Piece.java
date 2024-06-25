package com.wootecam.chess.pieces;

public class Piece {

    private final Color color;
    private final Type type;

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public static Piece createWhitePawn() {
        return new Piece(Color.WHITE, Type.PAWN);
    }

    public static Piece createBlackPawn() {
        return new Piece(Color.BLACK, Type.PAWN);
    }

    public static Piece createWhiteRook() {
        return new Piece(Color.WHITE, Type.ROOK);
    }

    public static Piece createBlackRook() {
        return new Piece(Color.BLACK, Type.ROOK);
    }

    public static Piece createWhiteKnight() {
        return new Piece(Color.WHITE, Type.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return new Piece(Color.BLACK, Type.KNIGHT);
    }

    public static Piece createWhiteBishop() {
        return new Piece(Color.WHITE, Type.BISHOP);
    }

    public static Piece createBlackBishop() {
        return new Piece(Color.BLACK, Type.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return new Piece(Color.WHITE, Type.QUEEN);
    }

    public static Piece createBlackQueen() {
        return new Piece(Color.BLACK, Type.QUEEN);
    }

    public static Piece createWhiteKing() {
        return new Piece(Color.WHITE, Type.KING);
    }

    public static Piece createBlackKing() {
        return new Piece(Color.BLACK, Type.KING);
    }

    public static Piece createBlank() {
        return new Piece(Color.NO_COLOR, Type.NO_PIECE);
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public String getRepresentation() {
        return type.findRepresentation(color);
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }
}
