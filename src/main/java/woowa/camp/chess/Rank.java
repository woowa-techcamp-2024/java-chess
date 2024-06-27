package woowa.camp.chess;

import static woowa.camp.chess.BoardConstants.MAX_COL;

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
        IntStream.range(0, MAX_COL.getCount()).forEach(count -> {
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
