package woowa.camp.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import woowa.camp.pieces.Piece;

public class Rank {

    private final List<Piece> pieces = new ArrayList<>();

    public Rank() {
        initRank();
    }

    public void initRank() {
        IntStream.range(0, Board.MAX_COL).forEach(count -> {
            pieces.add(Piece.createBlank());
        });
    }

    public void replace(final int index, final Piece piece) {
        pieces.set(index, piece);
    }

    public Piece get(final int index) {
        return pieces.get(index);
    }

    public int size() {
        return pieces.size();
    }

}
