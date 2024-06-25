package com.wootecam.chess.pieces;

public class Piece {
    private static final Color DEFAULT_COLOR = Color.WHITE;

    private final PieceType type;
    private final Color color;
    private final PieceRepresentation representation;

    public Piece(PieceType pieceType) {
        this.type = pieceType;
        this.color = DEFAULT_COLOR;
        this.representation = PieceRepresentation.findByTypeAndColor(pieceType, DEFAULT_COLOR);
    }

    public Piece(PieceType pieceType, Color color) {
        this.type = pieceType;
        this.color = color;
        this.representation = PieceRepresentation.findByTypeAndColor(pieceType, color);
    }

    public static Piece createWhitePawn() {
        return new Pawn(PieceType.PAWN, Color.WHITE);
    }

    public static Piece createWhiteKnight() {
        return new Knight(PieceType.KNIGHT, Color.WHITE);
    }

    public static Piece createWhiteRook() {
        return new Rook(PieceType.ROOK, Color.WHITE);
    }

    public static Piece createWhiteBishop() {
        return new Bishop(PieceType.BISHOP, Color.WHITE);
    }

    public static Piece createWhiteQueen() {
        return new Queen(PieceType.QUEEN, Color.WHITE);
    }

    public static Piece createWhiteKing() {
        return new King(PieceType.KING, Color.WHITE);
    }

    public static Piece createBlackPawn() {
        return new Pawn(PieceType.PAWN, Color.BLACK);
    }

    public static Piece createBlackKnight() {
        return new Knight(PieceType.KNIGHT, Color.BLACK);
    }

    public static Piece createBlackRook() {
        return new Rook(PieceType.ROOK, Color.BLACK);
    }

    public static Piece createBlackBishop() {
        return new Bishop(PieceType.BISHOP, Color.BLACK);
    }

    public static Piece createBlackQueen() {
        return new Queen(PieceType.QUEEN, Color.BLACK);
    }

    public static Piece createBlackKing() {
        return new King(PieceType.KING, Color.BLACK);
    }

    public Color getColor() {
        return color;
    }

    public PieceRepresentation getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return representation.value;
    }
}
