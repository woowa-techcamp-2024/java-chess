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

    public Pawn findPawn(final int pawnIndex) {
        validateFindPawn(pawnIndex);
        return pawns.get(pawnIndex);
    }

    private void validateFindPawn(final int pawnIndex) {
        final int pawnSize = size();
        if (isOutOfRange(pawnSize, pawnIndex)) {
            throw new IllegalArgumentException(String.format("범위를 벗어난 PawnIndex = %d, 현재 Pawn Size = %d",
                    pawnIndex, pawnSize));
        }
    }

    private boolean isOutOfRange(int size, int index) {
        return index < 0 || index >= size;
    }

}
