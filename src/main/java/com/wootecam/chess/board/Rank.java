package com.wootecam.chess.board;

import static com.wootecam.chess.board.Board.MAX_COL;
import static com.wootecam.chess.constraint.ChessConstraint.validFileIndex;

import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.property.Color;
import com.wootecam.chess.pieces.property.PieceType;
import java.util.Arrays;
import java.util.List;

public class Rank {
    private final Piece[] squares;

    public Rank() {
        this.squares = new Piece[MAX_COL];
        Arrays.fill(squares, Piece.BLANK);
    }

    public void place(Piece piece, int index) {
        validFileIndex(index);

        squares[index] = piece;
    }

    public void clearSquare(int index) {
        validFileIndex(index);

        squares[index] = Piece.BLANK;
    }

    public Piece get(int index) {
        validFileIndex(index);

        return squares[index];
    }

    public int countPiece(PieceType type, Color color) {
        return (int) Arrays.stream(squares)
                .filter(piece -> piece.hasTypeAndColor(type, color))
                .count();
    }

    public List<Piece> getPieces() {
        return Arrays.stream(squares).toList();
    }

    public List<Piece> getPieces(Color color) {
        return Arrays.stream(squares)
                .filter(p -> p.isColor(color))
                .toList();
    }
}
