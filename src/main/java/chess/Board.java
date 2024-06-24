package chess;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Pawn> pawns;

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(int index) {
        return pawns.get(index);
    }

    public void add(Pawn pawn) {
        pawns.add(pawn);
    }
    public Board() {
        pawns = new ArrayList<>();
    }
}
