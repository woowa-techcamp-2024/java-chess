package chess.board;

import chess.pieces.Piece;

import java.util.Comparator;

public enum Order {
    ASC(Comparator.comparingDouble((o1) -> o1.getType().getDefaultPoint())),
    DESC(((o1, o2) -> Double.compare(o2.getType().getDefaultPoint(), o1.getType().getDefaultPoint()))),;

    private final Comparator<Piece> comparator;

    Order(Comparator<Piece> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Piece> getComparator() {
        return comparator;
    }

}
