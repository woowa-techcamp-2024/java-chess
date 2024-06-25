package com.wootecam.chess.pieces;

public class Piece {

    public static final String COLOR_WHITE = "white";
    public static final String COLOR_BLACK = "black";
    public static final String WHITE_PAWN_REPRESENTATION = "p";
    public static final String BLACK_PAWN_REPRESENTATION = "P";
    public static final String WHITE_KNIGHT_REPRESENTATION = "n";
    public static final String BLACK_KNIGHT_REPRESENTATION = "N";
    public static final String WHITE_ROOK_REPRESENTATION = "r";
    public static final String BLACK_ROOK_REPRESENTATION = "R";
    public static final String WHITE_BISHOP_REPRESENTATION = "b";
    public static final String BLACK_BISHOP_REPRESENTATION = "B";
    public static final String WHITE_QUEEN_REPRESENTATION = "q";
    public static final String BLACK_QUEEN_REPRESENTATION = "Q";
    public static final String WHITE_KING_REPRESENTATION = "k";
    public static final String BLACK_KING_REPRESENTATION = "K";

    private final String color;
    private final String representation;

    public Piece(String color, String representation) {
        this.color = color;
        this.representation = representation;
    }

    public static Piece createWhitePawn() {
        return new Piece(COLOR_WHITE, WHITE_PAWN_REPRESENTATION);
    }

    public static Piece createBlackPawn() {
        return new Piece(COLOR_BLACK, BLACK_PAWN_REPRESENTATION);
    }

    public static Piece createWhiteKnight() {
        return new Piece(COLOR_WHITE, WHITE_KNIGHT_REPRESENTATION);
    }

    public static Piece createBlackKnight() {
        return new Piece(COLOR_BLACK, BLACK_KNIGHT_REPRESENTATION);
    }

    public static Piece createWhiteRook() {
        return new Piece(COLOR_WHITE, WHITE_ROOK_REPRESENTATION);
    }

    public static Piece createBlackRook() {
        return new Piece(COLOR_BLACK, BLACK_ROOK_REPRESENTATION);
    }

    public static Piece createWhiteBishop() {
        return new Piece(COLOR_WHITE, WHITE_BISHOP_REPRESENTATION);
    }

    public static Piece createBlackBishop() {
        return new Piece(COLOR_BLACK, BLACK_BISHOP_REPRESENTATION);
    }

    public static Piece createWhiteQueen() {
        return new Piece(COLOR_WHITE, WHITE_QUEEN_REPRESENTATION);
    }

    public static Piece createBlackQueen() {
        return new Piece(COLOR_BLACK, BLACK_QUEEN_REPRESENTATION);
    }

    public static Piece createWhiteKing() {
        return new Piece(COLOR_WHITE, WHITE_KING_REPRESENTATION);
    }

    public static Piece createBlackKing() {
        return new Piece(COLOR_BLACK, BLACK_KING_REPRESENTATION);
    }

    public String getColor() {
        return color;
    }

    public String getRepresentation() {
        return representation;
    }

    public boolean isWhite() {
        return color.equals(COLOR_WHITE);
    }

    public boolean isBlack() {
        return color.equals(COLOR_BLACK);
    }
}
