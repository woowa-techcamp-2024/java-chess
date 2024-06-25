package com.seong.chess;

import com.seong.chess.pieces.Pawn;
import com.seong.chess.pieces.Piece;
import com.seong.chess.pieces.Piece.Colors;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int BLACK_PAWN_LINE = 1;
    private static final int WHITE_PAWN_LINE = 6;

    private final List<Piece> pieces = new ArrayList<>();
    private final List<Column> columns = new ArrayList<>();

    public void add(Piece piece) {
        pieces.add(piece);
    }

    public int size() {
        return pieces.size();
    }

    public Piece findPawn(int index) {
        return pieces.stream()
                .filter(piece -> piece instanceof Pawn)
                .toList()
                .get(index);
    }

    public void initialize() {
        initializeBlank();
        initializePawn(BLACK_PAWN_LINE, Colors.BLACK_COLOR);
        initializePawn(WHITE_PAWN_LINE, Colors.WHITE_COLOR);
    }

    private void initializeBlank() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                columns.add(new Column(new Point(i, j)));
            }
        }
    }

    private void initializePawn(int row, String color) {
        columns.stream()
                .filter(column -> column.isSameRow(row))
                .forEach(column -> {
                    Pawn pawn = new Pawn(color);
                    pieces.add(pawn);
                    column.initialize(pawn);
                });
    }

    public String getWhitePawnsResult() {
        return getPawnResult(WHITE_PAWN_LINE);
    }

    public String getBlackPawnsResult() {
        return getPawnResult(BLACK_PAWN_LINE);
    }

    private String getPawnResult(int row) {
        StringBuilder sb = new StringBuilder();
        columns.stream()
                .filter(column -> column.isSameRow(row))
                .forEach(column -> sb.append(column.getPawnRepresentation()));
        return sb.toString();
    }
}
