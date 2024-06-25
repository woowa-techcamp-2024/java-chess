package org.example.chess.board;

import java.util.ArrayList;
import java.util.List;
import org.example.chess.pieces.Pawn;

public class Board {

    private final int MAX_PAWNS = 32;
    private final List<Pawn> pawns = new ArrayList<>();

    public void add(Pawn pawn) {
        if (pawns.size() > MAX_PAWNS) {
            throw new IllegalArgumentException("한 보드판에는 최대 32개의 말이 존재할 수 있습니다.");
        }
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(int i) {
        if (i < 0 || i >= pawns.size()) {
            throw new IllegalArgumentException("존재하지 않는 말입니다.");
        }
        return pawns.get(i);
    }

}
