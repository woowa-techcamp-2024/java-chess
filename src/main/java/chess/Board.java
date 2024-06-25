package chess;

import java.util.ArrayList;
import java.util.List;
import pieces.Pawn;

public class Board {

    private final List<Pawn> boards = new ArrayList<>();

    public void add(Pawn pawn) {
        boards.add(pawn);
    }

    public int size() {
        return boards.size();
    }

    public Pawn findPawn(int i) {
        return boards.get(i);
    }
}
