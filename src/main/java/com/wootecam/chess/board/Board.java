package com.wootecam.chess.board;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.PieceType;
import com.wootecam.chess.utils.StringUtils;
import java.util.Arrays;

public class Board {
    public static final int MAX_COL = 8;
    private static final int MAX_ROW = 8;
    public static final int TOTAL_CELLS = MAX_ROW * MAX_COL;

    private final Piece[][] cells;
    private int totalPieces;

    public Board() {
        this.cells = new Piece[MAX_ROW][MAX_COL];
        for (Piece[] rows : cells) {
            Arrays.fill(rows, Piece.createBlank());
        }
    }

    public void initialize() {
        addBlackPieces();
        addWhitePieces();
    }

    private void addBlackPieces() {
        int startIdx = 0;

        add(Piece.createBlackRook(), startIdx++);
        add(Piece.createBlackKnight(), startIdx++);
        add(Piece.createBlackBishop(), startIdx++);
        add(Piece.createBlackQueen(), startIdx++);
        add(Piece.createBlackKing(), startIdx++);
        add(Piece.createBlackBishop(), startIdx++);
        add(Piece.createBlackKnight(), startIdx++);
        add(Piece.createBlackRook(), startIdx++);
        for (int i = 0; i < MAX_COL; ++i) {
            add(Piece.createBlackPawn(), startIdx++);
        }
    }

    private void addWhitePieces() {
        int startIdx = TOTAL_CELLS - 2 * MAX_COL;

        for (int i = 0; i < MAX_COL; ++i) {
            add(Piece.createWhitePawn(), startIdx++);
        }
        add(Piece.createWhiteRook(), startIdx++);
        add(Piece.createWhiteKnight(), startIdx++);
        add(Piece.createWhiteBishop(), startIdx++);
        add(Piece.createWhiteQueen(), startIdx++);
        add(Piece.createWhiteKing(), startIdx++);
        add(Piece.createWhiteBishop(), startIdx++);
        add(Piece.createWhiteKnight(), startIdx++);
        add(Piece.createWhiteRook(), startIdx);
    }

    public void add(Piece piece) {
        if (totalPieces >= TOTAL_CELLS) {
            throw new IllegalArgumentException("Cannot add anymore pieces");
        }
        add(piece, totalPieces);
    }

    public void add(Piece piece, int index) {
        int row = index / MAX_COL;
        int col = index % MAX_COL;
        if (row < 0 || col < 0 || row >= MAX_ROW) {
            throw new IllegalArgumentException("The index is invalid: " + index);
        }
        if (cells[row][col].isPiece()) {
            throw new IllegalArgumentException("The cell is already occupied");
        }

        cells[row][col] = piece;
        if (piece.isPiece()) {
            ++totalPieces;
        }
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
        curState.append(piece.getRepresentation().value);
    }

    public int findPiece(PieceType type, Color color) {
        int count = 0;

        for (int r = 0; r < MAX_ROW; ++r) {
            for (int c = 0; c < MAX_COL; ++c) {
                if (cells[r][c].getType() == type && cells[r][c].getColor() == color) {
                    ++count;
                }
            }
        }

        return count;
    }

    public int size() {
        return totalPieces;
    }
}
