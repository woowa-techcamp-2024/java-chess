package chess.board.calculator;

import chess.pieces.Piece;
import java.util.Comparator;

public enum OrderBy {
    ASC((p1, p2) -> {
        if (p1.getType() == p2.getType()) return 0;
        return p1.getType().getPoint() < p2.getType().getPoint() ? -1 : 1;
    }),
    DESC((p1, p2) -> {
        if (p1.getType() == p2.getType()) return 0;
        return p1.getType().getPoint() < p2.getType().getPoint() ? 1 : -1;
    });

    private final Comparator<Piece> comparator;

    private OrderBy(Comparator<Piece> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Piece> getComparator() {
        return comparator;
    }
}
