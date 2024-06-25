package com.wootecam.chess.board;

import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.PieceRepresentation;
import com.wootecam.chess.utils.StringUtils;

public class ChessBoard {
    private static final int MAX_ROW = 8;
    public static final int MAX_COL = 8;
    public static final int TOTAL_CELLS = MAX_ROW * MAX_COL;

    private final Piece[][] cells;
    private int totalPieces;

    public ChessBoard() {
        this.cells = new Piece[MAX_ROW][MAX_COL];
    }

    public void add(Piece piece) {
        if (totalPieces >= TOTAL_CELLS) {
            throw new IllegalArgumentException("Cannot add anymore pawns");
        }
        add(piece, totalPieces);
    }

    public void add(Piece piece, int index) {
        int row = index / MAX_COL;
        int col = index % MAX_COL;
        if (row < 0 || col < 0 || row >= MAX_ROW) {
            throw new IllegalArgumentException("The index is invalid: " + index);
        }
        if (cells[row][col] != null) {
            throw new IllegalArgumentException("The cell is already occupied");
        }

        cells[row][col] = piece;
        ++totalPieces;
    }

    public String print() {
        StringBuilder curState = new StringBuilder();

        for (Piece[] rows : cells) {
            for (Piece cell : rows) {
                updateCurState(cell, curState);
            }
            StringUtils.appendNewLine(curState);
        }

        return curState.toString();
    }

    private void updateCurState(Piece piece, StringBuilder curState) {
        if (piece == null) {
            curState.append(PieceRepresentation.NONE.value);
            return;
        }

        curState.append(piece.getRepresentation().value);
    }
}
