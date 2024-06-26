package com.wootecam.chess.pieces;

public class Piece {
    private final PieceType type;
    private final Color color;
    private final PieceRepresentation representation;

    protected Piece(PieceType pieceType, Color color) {
        this.type = pieceType;
        this.color = color;
        this.representation = PieceRepresentation.findByTypeAndColor(pieceType, color);
    }

    public static Piece createWhitePawn() {
        return createWhite(PieceType.PAWN);
    }

    public static Piece createWhiteKnight() {
        return createWhite(PieceType.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return createWhite(PieceType.ROOK);
    }

    public static Piece createWhiteBishop() {
        return createWhite(PieceType.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return createWhite(PieceType.QUEEN);
    }

    public static Piece createWhiteKing() {
        return createWhite(PieceType.KING);
    }

    public static Piece createBlackPawn() {
        return createBlack(PieceType.PAWN);
    }

    public static Piece createBlackKnight() {
        return createBlack(PieceType.KNIGHT);
    }

    public static Piece createBlackRook() {
        return createBlack(PieceType.ROOK);
    }

    public static Piece createBlackBishop() {
        return createBlack(PieceType.BISHOP);
    }

    public static Piece createBlackQueen() {
        return createBlack(PieceType.QUEEN);
    }

    public static Piece createBlackKing() {
        return createBlack(PieceType.KING);
    }

    public static Piece createBlank() {
        return new Piece(PieceType.NO_PIECE, Color.NO_COLOR);
    }

    private static Piece createWhite(PieceType type) {
        return createPiece(type, Color.WHITE);
    }

    private static Piece createBlack(PieceType type) {
        return createPiece(type, Color.BLACK);
    }

    private static Piece createPiece(PieceType type, Color color) {
        return switch (type) {
            case PAWN -> new Pawn(type, color);
            case KNIGHT -> new Knight(type, color);
            case ROOK -> new Rook(type, color);
            case BISHOP -> new Bishop(type, color);
            case QUEEN -> new Queen(type, color);
            case KING -> new King(type, color);
            default -> new Piece(PieceType.NO_PIECE, Color.NO_COLOR);
        };
    }

    public boolean isWhite() {
        return color.isWhite();
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public PieceRepresentation getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return color.name() + " " + type.name();
    }
}
