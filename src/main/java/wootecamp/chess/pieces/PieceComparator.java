package wootecamp.chess.pieces;

import java.util.Comparator;

public class PieceComparator {
    public static final PieceComparator ASC = new PieceComparator(
            Comparator.<Piece>comparingDouble(piece -> piece.getType().getPoint())
    );
    public static final PieceComparator DESC = new PieceComparator(
            Comparator.<Piece>comparingDouble(piece -> piece.getType().getPoint()).reversed()
    );

    private final Comparator<Piece> comparator;

    private PieceComparator(Comparator<Piece> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Piece> getComparator() {
        return comparator;
    }
}
