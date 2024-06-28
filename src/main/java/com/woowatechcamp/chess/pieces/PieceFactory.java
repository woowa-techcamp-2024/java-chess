package com.woowatechcamp.chess.pieces;

public class PieceFactory {
    private PieceFactory() {
    }

    ;

    public static Piece createWhitePawn(Position position) {
        return new Pawn(Piece.Color.WHITE, position);
    }

    public static Piece createBlackPawn(Position position) {
        return new Pawn(Piece.Color.BLACK, position);
    }

    public static Piece createWhiteRook(Position position) {
        return new Rook(Piece.Color.WHITE, position);
    }

    public static Piece createBlackRook(Position position) {
        return new Rook(Piece.Color.BLACK, position);
    }

    public static Piece createWhiteKnight(Position position) {
        return new Knight(Piece.Color.WHITE, position);
    }

    public static Piece createBlackKnight(Position position) {
        return new Knight(Piece.Color.BLACK, position);
    }

    public static Piece createWhiteBishop(Position position) {
        return new Bishop(Piece.Color.WHITE, position);
    }

    public static Piece createBlackBishop(Position position) {
        return new Bishop(Piece.Color.BLACK, position);
    }

    public static Piece createWhiteQueen(Position position) {
        return new Queen(Piece.Color.WHITE, position);
    }

    public static Piece createBlackQueen(Position position) {
        return new Queen(Piece.Color.BLACK, position);
    }

    public static Piece createWhiteKing(Position position) {
        return new King(Piece.Color.WHITE, position);
    }

    public static Piece createBlackKing(Position position) {
        return new King(Piece.Color.BLACK, position);
    }

    public static Piece createBlank(Position position) {
        return new Blank(position);
    }
}
