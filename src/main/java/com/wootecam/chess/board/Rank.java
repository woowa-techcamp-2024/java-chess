package com.wootecam.chess.board;

import static com.wootecam.chess.board.Board.MAX_COL;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.PieceType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rank {
    private final Piece[] squares;

    public Rank() {
        this.squares = new Piece[MAX_COL];
        Arrays.fill(squares, Piece.BLANK);
    }

    public void place(Piece piece, int index) {
        checkValidIndex(index);
        checkEmptySquare(index);

        squares[index] = piece;
    }

    public void clearSquare(int index) {
        checkValidIndex(index);

        squares[index] = Piece.BLANK;
    }

    private void checkValidIndex(int index) {
        if (index < 0 || index >= MAX_COL) {
            throw new IllegalArgumentException("The index is invalid: " + index);
        }
    }

    private void checkEmptySquare(int index) {
        if (squares[index].isPiece()) {
            throw new IllegalArgumentException("The square is already occupied");
        }
    }

    public String print() {
        return Arrays.stream(squares)
                .map(Piece::getRepresentation)
                .map(r -> r.value)
                .collect(Collectors.joining());
    }

    public Piece get(int index) {
        checkValidIndex(index);

        return squares[index];
    }

    public int countPiece(PieceType type, Color color) {
        return (int) Arrays.stream(squares)
                .filter(piece -> piece.hasTypeAndColor(type, color))
                .count();
    }

    public List<Piece> getPieces(Color color) {
        return Arrays.stream(squares)
                .filter(p -> p.isColor(color))
                .toList();
    }
}
