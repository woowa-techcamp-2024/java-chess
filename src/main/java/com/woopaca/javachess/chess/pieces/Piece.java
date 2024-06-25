package com.woopaca.javachess.chess.pieces;

public class Piece {

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    private final String color;
    private final PieceType type;

    private Piece(final String color, final PieceType type) {
        this.color = color;
        this.type = type;
    }

    public static Piece createWhitePawn() {
        return new Piece(WHITE_COLOR, PieceType.PAWN);
    }

    public static Piece createBlackPawn() {
        return new Piece(BLACK_COLOR, PieceType.PAWN);
    }

    public static Piece createWhiteKnight() {
        return new Piece(WHITE_COLOR, PieceType.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return new Piece(BLACK_COLOR, PieceType.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return new Piece(WHITE_COLOR, PieceType.ROOK);
    }

    public static Piece createBlackRook() {
        return new Piece(BLACK_COLOR, PieceType.ROOK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(WHITE_COLOR, PieceType.BISHOP);
    }

    public static Piece createBlackBishop() {
        return new Piece(BLACK_COLOR, PieceType.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return new Piece(WHITE_COLOR, PieceType.QUEEN);
    }

    public static Piece createBlackQueen() {
        return new Piece(BLACK_COLOR, PieceType.QUEEN);
    }

    public static Piece createWhiteKing() {
        return new Piece(WHITE_COLOR, PieceType.KING);
    }

    public static Piece createBlackKing() {
        return new Piece(BLACK_COLOR, PieceType.KING);
    }

    public String getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public char getRepresentation() {
        if (color.equals(WHITE_COLOR)) {
            return type.getWhiteRepresentation();
        }
        return type.getBlackRepresentation();
    }

    public boolean isWhite() {
        return color.equals(WHITE_COLOR);
    }

    public boolean isBlack() {
        return color.equals(BLACK_COLOR);
    }

}
