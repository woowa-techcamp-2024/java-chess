package com.wootecam.chess.board;

import com.wootecam.chess.pieces.Piece;
import java.util.Comparator;

public enum Order {
    ASC,
    DESC;

    private static final Comparator<Piece> AscComparator = Comparator.comparingDouble(o -> o.getType().point);
    private static final Comparator<Piece> DescComparator = Comparator.comparingDouble((Piece o) -> o.getType().point)
            .reversed();

    public int compare(Piece p1, Piece p2) {
        if (this == ASC) {
            return AscComparator.compare(p1, p2);
        }
        return DescComparator.compare(p1, p2);
    }
}
