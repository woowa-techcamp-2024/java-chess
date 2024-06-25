package com.wootecam.chess;

import com.wootecam.chess.pieces.Pawn;
import com.wootecam.chess.pieces.PieceRepresentation;

public class ChessBoard {
    private static final int MAX_ROW = 8;
    public static final int MAX_COL = 8;
    private static final int TOTAL_CELLS = MAX_ROW * MAX_COL;

    private final Pawn[][] cells;
    private int totalPieces;

    public ChessBoard() {
        this.cells = new Pawn[MAX_ROW][MAX_COL];
    }

    public void add(Pawn pawn) {
        if (totalPieces >= TOTAL_CELLS) {
            throw new IllegalArgumentException("Cannot add anymore pawns");
        }
        add(pawn, totalPieces);
    }

    public void add(Pawn pawn, int index) {
        int row = index / MAX_COL;
        int col = index % MAX_COL;
        if (row < 0 || col < 0 || row >= MAX_ROW) {
            throw new IllegalArgumentException("The index is invalid: " + index);
        }
        if (cells[row][col] != null) {
            throw new IllegalArgumentException("The cell is already occupied");
        }

        cells[row][col] = pawn;
        ++totalPieces;
    }

    public int size() {
        return TOTAL_CELLS;
    }

    public String print() {
        StringBuilder curState = new StringBuilder();

        for (Pawn[] rows : cells) {
            for (Pawn cell : rows) {
                updateCurState(cell, curState);
            }
            curState.append(System.lineSeparator());
        }

        return curState.toString();
    }

    private void updateCurState(Pawn pawn, StringBuilder curState) {
        if (pawn == null) {
            curState.append(PieceRepresentation.NONE.value);
            return;
        }

        curState.append(pawn.getRepresentation().value);
    }
}
