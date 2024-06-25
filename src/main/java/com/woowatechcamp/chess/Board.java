package com.woowatechcamp.chess;

import com.woowatechcamp.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Pawn> pawns;

    public Board() {
        pawns = new ArrayList<>();
    }

    public void add(Pawn pawn) {
        pawns.add(pawn);
    }

    public Pawn findPawn(int index) {
        return pawns.get(index);
    }

    public int size() {
        return pawns.size();
    }
}
