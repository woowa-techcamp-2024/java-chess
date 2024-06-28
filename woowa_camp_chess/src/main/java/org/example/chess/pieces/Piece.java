package org.example.chess.pieces;

import org.example.chess.board.Board;
import org.example.chess.board.Position;

import java.util.List;
import java.util.Objects;

import static org.example.chess.pieces.Piece.Color.BLACK;
import static org.example.chess.pieces.Piece.Color.WHITE;


public abstract class Piece {

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public enum Color {
        WHITE, BLACK, NOCOLOR;

        public boolean isOpposite(Color color) {
            return this.equals(WHITE)&&color.equals(BLACK) || this.equals(BLACK)&&color.equals(WHITE);
        }

    }

    private final Color color;
    private final char representation;
    private final PieceType pieceType;
    private int moveCount = 0;

    public void increaseMoveCount() {
        moveCount++;
    }

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

    public abstract List<Position> getPossibleMovePosition(Position srcPosition, Board board);


}
