package org.example.chess.pieces;

public class Piece {


    private final String color;
    private final char representation;
    private final PieceType pieceType;
    public static final int CASE_DIFFERENCE = 32;
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final char WHITE_REPRESENTATION = 'p';
    public static final char BLACK_REPRESENTATION = 'P';

    public Piece(String color, PieceType pieceType) {
        this.color = color;
        this.representation = color.equals(WHITE_COLOR) ? (char) (pieceType.getAbbreviation() + CASE_DIFFERENCE)  : pieceType.getAbbreviation();
        this.pieceType = pieceType;
    }

    public Piece() {
        this.representation = (char) (PieceType.PAWN.getAbbreviation()+32);
        System.out.println(representation);
        this.pieceType = PieceType.PAWN;
        this.color = WHITE_COLOR;
    }

    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }

    public static Piece createBlackPawn() {
        return new Piece(Piece.BLACK_COLOR, PieceType.PAWN);
    }

    public static Piece createWhitePawn() {
        return new Piece(Piece.WHITE_COLOR, PieceType.PAWN);
    }

    public static Piece createBlackKnight() {
        return new Piece(Piece.BLACK_COLOR, PieceType.KNIGHT);
    }

    public static Piece createWhiteKnight() {
        return new Piece(Piece.WHITE_COLOR, PieceType.KNIGHT);
    }

    public static Piece createBlackBishop() {
        return new Piece(Piece.BLACK_COLOR, PieceType.BISHOP);
    }

    public static Piece createWhiteBishop() {
        return new Piece(Piece.WHITE_COLOR, PieceType.BISHOP);
    }

    public static Piece createBlackRook() {
        return new Piece(Piece.BLACK_COLOR, PieceType.ROOK);
    }

    public static Piece createWhiteRook() {
        return new Piece(Piece.WHITE_COLOR, PieceType.ROOK);
    }

    public static Piece createBlackQueen() {
        return new Piece(Piece.BLACK_COLOR, PieceType.QUEEN);
    }

    public static Piece createWhiteQueen() {
        return new Piece(Piece.WHITE_COLOR, PieceType.QUEEN);
    }

    public static Piece createBlackKing() {
        return new Piece(Piece.BLACK_COLOR, PieceType.KING);
    }

    public static Piece createWhiteKing() {
        return new Piece(Piece.WHITE_COLOR, PieceType.KING);
    }

    public boolean isWhite() {
        return this.color.equals(Piece.WHITE_COLOR);
    }

    public boolean isBlack() {
        return this.color.equals(Piece.BLACK_COLOR);
    }
}
