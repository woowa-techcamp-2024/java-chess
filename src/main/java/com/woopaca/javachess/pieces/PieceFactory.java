package com.woopaca.javachess.pieces;

import com.woopaca.javachess.chess.Position;

public class PieceFactory {

    private PieceFactory() {
    }

    public static Piece createWhitePawn(Position position) {
        return createWhite(Type.PAWN, position);
    }

    public static Piece createBlackPawn(Position position) {
        return createBlack(Type.PAWN, position);
    }

    public static Piece createWhiteKnight(Position position) {
        return createWhite(Type.KNIGHT, position);
    }

    public static Piece createBlackKnight(Position position) {
        return createBlack(Type.KNIGHT, position);
    }

    public static Piece createWhiteRook(Position position) {
        return createWhite(Type.ROOK, position);
    }

    public static Piece createBlackRook(Position position) {
        return createBlack(Type.ROOK, position);
    }

    public static Piece createWhiteBishop(Position position) {
        return createWhite(Type.BISHOP, position);
    }

    public static Piece createBlackBishop(Position position) {
        return createBlack(Type.BISHOP, position);
    }

    public static Piece createWhiteQueen(Position position) {
        return createWhite(Type.QUEEN, position);
    }

    public static Piece createBlackQueen(Position position) {
        return createBlack(Type.QUEEN, position);
    }

    public static Piece createWhiteKing(Position position) {
        return createWhite(Type.KING, position);
    }

    public static Piece createBlackKing(Position position) {
        return createBlack(Type.KING, position);
    }

    public static Piece createBlank(Position position) {
        return new Blank(Color.NOCOLOR, position);
    }

    private static Piece createWhite(Type type, Position position) {
        return create(Color.WHITE, type, position);
    }

    private static Piece createBlack(Type type, Position position) {
        return create(Color.BLACK, type, position);
    }

    private static Piece create(Color color, Type type, Position position) {
        return switch (type) {
            case PAWN -> new Pawn(color, position);
            case KNIGHT -> new Knight(color, position);
            case BISHOP -> new Bishop(color, position);
            case ROOK -> new Rook(color, position);
            case QUEEN -> new Queen(color, position);
            case KING -> new King(color, position);
            case NO_PIECE -> new Blank(color, position);
        };
    }
}
