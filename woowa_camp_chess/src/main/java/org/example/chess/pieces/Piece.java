package org.example.chess.pieces;

import org.example.chess.board.Position;

import java.util.Objects;

import static org.example.chess.pieces.Piece.Color.BLACK;
import static org.example.chess.pieces.Piece.Color.WHITE;


public abstract class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    private final Color color;
    private final char representation;
    private final PieceType pieceType;
    //todo : 현재는 임의로 아무것도 없는 List로 초기화 시킴

    protected Piece(Color color, PieceType pieceType) {
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

    public boolean isWhite() {
        return this.color.equals(WHITE);
    }

    public boolean isBlack() {
        return this.color.equals(BLACK);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece piece)) return false;
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
