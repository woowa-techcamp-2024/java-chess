package woowa.camp;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Pawn> pawns = new ArrayList<>();

    public void add(final Pawn pawn) {
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

}
