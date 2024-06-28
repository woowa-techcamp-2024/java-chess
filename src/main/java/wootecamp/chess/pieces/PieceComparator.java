package wootecamp.chess.pieces;

import java.util.Comparator;

public class PieceComparator {
    public static final PieceComparator ASC = new PieceComparator(
            (p1, p2) -> Double.compare(p1.getType().getPoint(), p2.getType().getPoint())
    );
    public static final PieceComparator DESC = new PieceComparator(
            (p1, p2) -> Double.compare(p2.getType().getPoint(), p1.getType().getPoint())
    );

    private final Comparator<Piece> comparator;

    private PieceComparator(Comparator<Piece> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Piece> getComparator() {
        return comparator;
    }
}
