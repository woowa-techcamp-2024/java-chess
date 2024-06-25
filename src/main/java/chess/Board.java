package chess;

import chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Pawn> pawns = new ArrayList<>(); // <1>

    public void add(Pawn pawn) {
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(int index) {
        return pawns.get(index);
    }
}
