package pe.goblin.board;

import pe.goblin.pawn.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int MAX_PAWN_PIECE = 2;

    private final List<Pawn> pawns = new ArrayList<>();

    public void add(Pawn pawn) throws ExceedPawnException {
        if (pawns.size() >= MAX_PAWN_PIECE) {
            throw new ExceedPawnException();
        }
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(int idx) {
        return pawns.get(idx);
    }
}
