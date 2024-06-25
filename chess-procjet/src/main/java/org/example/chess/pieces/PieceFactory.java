package org.example.chess.pieces;

public class PieceFactory {

    public static Piece createWhitePawn() {
        return Piece.createPiece(Color.WHITE, PieceName.PAWN);
    }

    public static Piece createBlackPawn() {
        return Piece.createPiece(Color.BLACK, PieceName.PAWN);
    }

    public static Piece createWhiteKnight() {
        return Piece.createPiece(Color.WHITE, PieceName.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return Piece.createPiece(Color.BLACK, PieceName.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return Piece.createPiece(Color.WHITE, PieceName.ROOK);
    }

    public static Piece createBlackRook() {
        return Piece.createPiece(Color.BLACK, PieceName.ROOK);
    }

    public static Piece createWhiteBishop() {
        return Piece.createPiece(Color.WHITE, PieceName.BISHOP);
    }

    public static Piece createBlackBishop() {
        return Piece.createPiece(Color.BLACK, PieceName.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return Piece.createPiece(Color.WHITE, PieceName.QUEEN);
    }

    public static Piece createBlackQueen() {
        return Piece.createPiece(Color.BLACK, PieceName.QUEEN);
    }

    public static Piece createWhiteKing() {
        return Piece.createPiece(Color.WHITE, PieceName.KING);
    }

    public static Piece createBlackKing() {
        return Piece.createPiece(Color.BLACK, PieceName.KING);
    }
}

