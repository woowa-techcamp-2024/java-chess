package com.wootecam.chess;

import com.wootecam.chess.pieces.Pawn;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Pawn> pawns;
    private int size;

    public Board() {
        this.pawns = new ArrayList<>();
        this.size = 0;
    }

    public void add(Pawn pawn) {
        pawns.add(pawn);
        ++size;
    }

    public int size() {
        return size;
    }

    public Pawn findPawn(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("The specified pawn is not found");
        }
        return pawns.get(index);
    }
}
