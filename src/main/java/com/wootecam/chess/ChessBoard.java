package com.wootecam.chess;

import com.wootecam.chess.pieces.Pawn;

public class ChessBoard {
    private static final int MAX_ROW = 8;
    private static final int MAX_COL = 8;

    private final Pawn[][] cells;
    private int totalPieces;

    public ChessBoard() {
        this.cells = new Pawn[MAX_ROW][MAX_COL];
    }

    public void add(Pawn pawn) {
        add(pawn, totalPieces);
    }

    public void add(Pawn pawn, int index) {
        int row = index / MAX_COL;
        int col = index % MAX_ROW;
        if (row < 0 || col < 0 || row >= MAX_ROW || col >= MAX_COL) {
            throw new IllegalArgumentException("The index is unvalid: " + index);
        }
        if (cells[row][col] != null) {
            throw new IllegalArgumentException("The cell is already occupied");
        }

        cells[row][col] = pawn;
        ++totalPieces;
    }
}
