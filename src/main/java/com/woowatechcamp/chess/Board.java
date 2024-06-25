package com.woowatechcamp.chess;

import com.woowatechcamp.chess.pieces.Color;
import com.woowatechcamp.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Pawn> whitePawns;
    private final List<Pawn> blackPawns;

    public Board() {
        whitePawns = new ArrayList<>();
        blackPawns = new ArrayList<>();
    }

    public void initialize() {
        for (int i = 0; i < 8; i++) {
            whitePawns.add(new Pawn(Color.WHITE));
            blackPawns.add(new Pawn(Color.BLACK));
        }
    }

    public void add(Pawn pawn) {
        if (pawn.getColor() == Color.WHITE) {
            whitePawns.add(pawn);
            return;
        }
        blackPawns.add(pawn);
    }

    public int size() {
        return whitePawns.size() + blackPawns.size();
    }

    public String getWhitePawnsResult() {
        StringBuilder result = new StringBuilder();
        whitePawns.forEach(pawn -> result.append(pawn.toString()));
        return result.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder result = new StringBuilder();
        blackPawns.forEach(pawn -> result.append(pawn.toString()));
        return result.toString();
    }
}
