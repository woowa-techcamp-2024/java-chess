package com.seong.chess;

import com.seong.chess.pieces.Pawn;
import com.seong.chess.pieces.Piece;
import com.seong.chess.pieces.Piece.Colors;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int BLACK_PAWN_LINE = 1;
    private static final int WHITE_PAWN_LINE = 6;
    private static final int BOARD_LENGTH = 8;

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
        columns.clear();
        initializeBlank();
        initializePawn(BLACK_PAWN_LINE, Colors.BLACK_COLOR);
        initializePawn(WHITE_PAWN_LINE, Colors.WHITE_COLOR);
    }

    private void initializeBlank() {
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
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
                .forEach(column -> sb.append(column.getRepresentation()));
        return sb.toString();
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_LENGTH * BOARD_LENGTH; i++) {
            if (i != 0 && i % BOARD_LENGTH == 0) {
                sb.append("\n");
            }
            sb.append(columns.get(i).getRepresentation());
        }
        return sb.toString();
    }
}
