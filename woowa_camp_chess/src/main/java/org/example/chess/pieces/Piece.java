package org.example.chess.pieces;

import org.example.chess.board.Position;

import java.util.Objects;

import static org.example.chess.pieces.Piece.Color.*;


public class Piece {


    public enum Color {
        WHITE, BLACK, NOCOLOR;

    }
    private Position position;
    private final Color color;

    private final char representation;

    private final PieceType pieceType;
    private Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.representation = color.equals(WHITE) ? pieceType.getWhiteRepresentation() : pieceType.getBlackRepresentation();
        this.pieceType = pieceType;
    }
    public Piece() {
        this.representation = (char) (PieceType.PAWN.getBlackRepresentation() + 32);
        System.out.println(representation);
        this.pieceType = PieceType.PAWN;
        this.color = WHITE;
    }

    public Color getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }

    public static Piece createPiece(Color color, PieceType pieceType) {
        return new Piece(color, pieceType);
    }

    public static Piece createBlank() {
        return new Piece(NOCOLOR, PieceType.NO_PIECE);
    }

    private static Piece createWhite(PieceType pieceType) {
        return new Piece(WHITE, pieceType);
    }

    private static Piece createBlack(PieceType pieceType) {
        return new Piece(BLACK, pieceType);
    }

    public static Piece createBlackPawn() {
        return createBlack(PieceType.PAWN);
    }

    public static Piece createWhitePawn() {
        return createWhite(PieceType.PAWN);
    }

    public static Piece createBlackKnight() {
        return createBlack(PieceType.KNIGHT);
    }

    public static Piece createWhiteKnight() {
        return createWhite(PieceType.KNIGHT);
    }

    public static Piece createBlackBishop() {
        return createBlack(PieceType.BISHOP);
    }

    public static Piece createWhiteBishop() {
        return createWhite(PieceType.BISHOP);
    }

    public static Piece createBlackRook() {
        return createBlack(PieceType.ROOK);
    }

    public static Piece createWhiteRook() {
        return createWhite(PieceType.ROOK);
    }

    public static Piece createBlackQueen() {
        return createBlack(PieceType.QUEEN);
    }

    public static Piece createWhiteQueen() {
        return createWhite(PieceType.QUEEN);
    }

    public static Piece createBlackKing() {
        return createBlack(PieceType.KING);
    }

    public static Piece createWhiteKing() {
        return createWhite(PieceType.KING);
    }

    public boolean isWhite() {
        return this.color.equals(WHITE);
    }

    public boolean isBlack() {
        return this.color.equals(BLACK);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return representation == piece.representation && color == piece.color && pieceType == piece.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, representation, pieceType);
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
