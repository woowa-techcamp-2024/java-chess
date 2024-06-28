package org.example.chess.pieces;

import org.example.chess.pieces.Piece.Color;

public class PieceFactory {
    public static Piece createBlank() {
        return new Blank();
    }

    public static Piece createWhitePawn() {
        return new Pawn(Color.WHITE);
    }

    public static Piece createBlackPawn() {
        return new Pawn(Color.BLACK);
    }

    public static Piece createWhiteKnight() {
        return new Knight(Color.WHITE);
    }

    public static Piece createBlackKnight() {
        return new Knight(Color.BLACK);
    }

    public static Piece createWhiteRook() {
        return new Rook(Color.WHITE);
    }

    public static Piece createBlackRook() {
        return new Rook(Color.BLACK);
    }

    public static Piece createWhiteBishop() {
        return new Bishop(Color.WHITE);
    }

    public static Piece createBlackBishop() {
        return new Bishop(Color.BLACK);
    }

    public static Piece createWhiteQueen() {
        return new Queen(Color.WHITE);
    }

    public static Piece createBlackQueen() {
        return new Queen(Color.BLACK);
    }

    public static Piece createWhiteKing() {
        return new King(Color.WHITE);
    }

    public static Piece createBlackKing() {
        return new King(Color.BLACK);
    }

}
