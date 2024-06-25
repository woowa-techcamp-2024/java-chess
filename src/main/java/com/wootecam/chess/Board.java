package com.wootecam.chess;

import com.wootecam.chess.pieces.Pawn;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Pawn> pawns = new ArrayList<>();

    public Board() {
    }

    public void add(final Pawn pawn) {
        pawns.add(pawn);
    }

    public Pawn findPawn(final int pawnIndex) {
        if (pawnIndex < 0 || pawns.size() <= pawnIndex) {
            String message = String.format("폰 인덱스는 0미만이거나 폰의 개수보다 크거나 같을 수 없습니다. size = %d", pawns.size());
            throw new IllegalArgumentException(message);
        }
        return pawns.get(pawnIndex);
    }

    public int size() {
        return pawns.size();
    }
}
