package chess;

import chess.piece.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Pawn> pieces = new ArrayList<>();

    public void add(Pawn pawn) {
        pieces.add(pawn);
    }

    public int size() {
        return pieces.size();
    }

    public Pawn findPawn(int index) {
        return pieces.get(index);
    }
}
