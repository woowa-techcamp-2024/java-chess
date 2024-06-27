package chess.sorter;

import chess.pieces.Piece;
import java.util.Comparator;
import java.util.List;

public class PointSorter implements Sorter {
    private static final Comparator<Piece> ascComparator = Comparator.comparingDouble(Piece::getDefaultPoint);
    private static final Comparator<Piece> descComparator = Comparator.comparingDouble(Piece::getDefaultPoint)
            .reversed();

    @Override
    public List<Piece> sort(List<Piece> pieces, Direction direction) {
        Comparator<Piece> comparator = direction.equals(Direction.ASC) ? ascComparator : descComparator;
        return pieces.stream()
                .sorted(comparator)
                .toList();
    }
}
