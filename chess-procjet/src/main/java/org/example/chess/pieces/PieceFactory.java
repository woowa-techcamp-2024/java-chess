package org.example.chess.pieces;

public class PieceFactory {

    public static Piece createWhitePawn() {
        return Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN);
    }

    public static Piece createBlackPawn() {
        return Piece.createPiece(Piece.Color.BLACK, Piece.Type.PAWN);
    }

    public static Piece createWhiteKnight() {
        return Piece.createPiece(Piece.Color.WHITE, Piece.Type.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return Piece.createPiece(Piece.Color.BLACK, Piece.Type.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return Piece.createPiece(Piece.Color.WHITE, Piece.Type.ROOK);
    }

    public static Piece createBlackRook() {
        return Piece.createPiece(Piece.Color.BLACK, Piece.Type.ROOK);
    }

    public static Piece createWhiteBishop() {
        return Piece.createPiece(Piece.Color.WHITE, Piece.Type.BISHOP);
    }

    public static Piece createBlackBishop() {
        return Piece.createPiece(Piece.Color.BLACK, Piece.Type.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return Piece.createPiece(Piece.Color.WHITE, Piece.Type.QUEEN);
    }

    public static Piece createBlackQueen() {
        return Piece.createPiece(Piece.Color.BLACK, Piece.Type.QUEEN);
    }

    public static Piece createWhiteKing() {
        return Piece.createPiece(Piece.Color.WHITE, Piece.Type.KING);
    }

    public static Piece createBlackKing() {
        return Piece.createPiece(Piece.Color.BLACK, Piece.Type.KING);
    }
}

