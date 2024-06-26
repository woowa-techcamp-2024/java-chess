package org.example.chess.board.sort;

import java.util.Comparator;
import org.example.chess.pieces.Piece;

public class DescendingPieceComparator implements PieceComparator {
    @Override
    public Comparator<Piece> getComparator() {
        return Comparator.comparingDouble((Piece p) -> p.getType().getDefaultPoint()).reversed();
    }
}
