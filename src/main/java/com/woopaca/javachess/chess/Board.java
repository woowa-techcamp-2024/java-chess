package com.woopaca.javachess.chess;

import com.woopaca.javachess.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    public static final int BOARD_SIZE = 8;
    public static final int WHITE_PAWNS_ROW = 6;
    public static final int BLACK_PAWNS_ROW = 1;

    private final List<Pawn>[] pawns = new ArrayList[BOARD_SIZE];

    {
        for (int i = 0; i < BOARD_SIZE; i++) {
            pawns[i] = new ArrayList<>(BOARD_SIZE);
            for (int j = 0; j < BOARD_SIZE; j++) {
                pawns[i].add(null);
            }
        }
    }

    public void add(Pawn pawn) {
        // FIXME 요구사항에 맞게 수정하기
        pawns[0].add(pawn);
    }

    public int size() {
        // FIXME 요구사항에 맞게 수정하기
        int size = 0;
        for (List<Pawn> pawn : pawns) {
            size += pawn.size();
        }
        return size;
    }

    public Pawn findPawn(int index) {
        // FIXME 요구사항에 맞게 수정하기
        return null;
    }

    public void initialize() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            addPawn(Pawn.WHITE_COLOR, WHITE_PAWNS_ROW);
            addPawn(Pawn.BLACK_COLOR, BLACK_PAWNS_ROW);
        }
    }

    private void addPawn(String color, int row) {
        Pawn pawn = new Pawn(color);
        if (pawns[row].size() >= BOARD_SIZE) {
            pawns[row].remove(0);
        }
        pawns[row].add(pawn);
    }

    public String getWhitePawnsResult() {
        List<Pawn> whitePawns = pawns[WHITE_PAWNS_ROW];
        return generatePawnsResult(whitePawns);
    }

    public String getBlackPawnsResult() {
        List<Pawn> blackPawns = pawns[BLACK_PAWNS_ROW];
        return generatePawnsResult(blackPawns);
    }

    public String print() {
        StringBuilder boardResult = new StringBuilder();
        for (List<Pawn> row : pawns) {
            String result = generatePawnsResult(row);
            boardResult.append(result)
                    .append(System.lineSeparator());
        }
        return boardResult.toString();
    }

    private String generatePawnsResult(List<Pawn> pawns) {
        return pawns.stream()
                .map(pawn -> pawn == null ? "." : String.valueOf(pawn.getRepresentation()))
                .collect(Collectors.joining());
    }

}
