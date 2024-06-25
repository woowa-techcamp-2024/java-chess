package chess;

import chess.pieces.Pawn;
import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Pawn> board = new ArrayList<>();

    public void add(Pawn pawn) {
        if (pawn == null) {
            throw new IllegalArgumentException("pawn must not be null");
        }
        board.add(pawn);
    }

    public int size() {
        return board.size();
    }

    public Pawn findPawn(int index) {
        return board.get(index);
    }
}
