package org.example.pieces;

import static org.example.pieces.Piece.Color.*;

public class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING, NO_PIECE;
    }

    public static final String WHITE_PAWN_REPRESENTATION = "\u2659";
    public static final String BLACK_PAWN_REPRESENTATION = "\u265F";

    public static final String WHITE_KNIGHT_REPRESENTATION = "\u2658";
    public static final String BLACK_KNIGHT_REPRESENTATION = "\u265E";

    public static final String WHITE_ROOK_REPRESENTATION = "\u2656";
    public static final String BLACK_ROOK_REPRESENTATION = "\u265C";

    public static final String WHITE_BISHOP_REPRESENTATION = "\u2657";
    public static final String BLACK_BISHOP_REPRESENTATION = "\u265D";

    public static final String WHITE_QUEEN_REPRESENTATION = "\u2655";
    public static final String BLACK_QUEEN_REPRESENTATION = "\u265B";

    public static final String WHITE_KING_REPRESENTATION = "\u2654";
    public static final String BLACK_KING_REPRESENTATION = "\u265A";

    private final Color color;
    private final String representation;

    private Piece(Color color, String representation) {
        this.color = color;
        this.representation = representation;
    }

    public static Piece createWhitePawn() {
        return new Piece(WHITE, WHITE_PAWN_REPRESENTATION);
    }

    public static Piece createBlackPawn() {
        return new Piece(BLACK, BLACK_PAWN_REPRESENTATION);
    }

    public static Piece createWhiteKnight() {
        return new Piece(WHITE, WHITE_KNIGHT_REPRESENTATION);
    }

    public static Piece createBlackKnight() {
        return new Piece(BLACK, BLACK_KNIGHT_REPRESENTATION);
    }

    public static Piece createWhiteRook() {
        return new Piece(WHITE, WHITE_ROOK_REPRESENTATION);
    }

    public static Piece createBlackRook() {
        return new Piece(BLACK, BLACK_ROOK_REPRESENTATION);
    }

    public static Piece createWhiteBishop() {
        return new Piece(WHITE, WHITE_BISHOP_REPRESENTATION);
    }

    public static Piece createBlackBishop() {
        return new Piece(BLACK, BLACK_BISHOP_REPRESENTATION);
    }

    public static Piece createWhiteQueen() {
        return new Piece(WHITE, WHITE_QUEEN_REPRESENTATION);
    }

    public static Piece createBlackQueen() {
        return new Piece(BLACK, BLACK_QUEEN_REPRESENTATION);
    }

    public static Piece createWhiteKing() {
        return new Piece(WHITE, WHITE_KING_REPRESENTATION);
    }

    public static Piece createBlackKing() {
        return new Piece(BLACK, BLACK_KING_REPRESENTATION);
    }

    public Color getColor() {
        return color;
    }

    public String getRepresentation() {
        return representation;
    }

    public boolean isWhite() {
        return color.equals(WHITE);
    }

    public boolean isBlack() {
        return color.equals(BLACK);
    }
}
