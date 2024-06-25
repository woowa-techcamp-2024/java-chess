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
            pawns[i] = new ArrayList<>();
        }
    }

    public void add(Pawn pawn) {
        pawns[0].add(pawn);
    }

    public int size() {
        int size = 0;
        for (List<Pawn> pawn : pawns) {
            size += pawn.size();
        }
        return size;
    }

    public Pawn findPawn(int index) {
        return null;
    }

    public void initialize() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Pawn whitePawn = new Pawn(Pawn.WHITE_COLOR);
            pawns[WHITE_PAWNS_ROW].add(whitePawn);

            Pawn blackPawn = new Pawn(Pawn.BLACK_COLOR);
            pawns[BLACK_PAWNS_ROW].add(blackPawn);
        }
    }

    public String getWhitePawnsResult() {
        List<Pawn> whitePawns = pawns[WHITE_PAWNS_ROW];
        return whitePawns.stream()
                .map(pawn -> String.valueOf(pawn.getRepresentation()))
                .collect(Collectors.joining());
    }

    public String getBlackPawnsResult() {
        List<Pawn> blackPawns = pawns[BLACK_PAWNS_ROW];
        return blackPawns.stream()
                .map(pawn -> String.valueOf(pawn.getRepresentation()))
                .collect(Collectors.joining());
    }

}
