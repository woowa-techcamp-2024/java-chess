package org.example.chess.board.sort;

import java.util.Comparator;
import org.example.chess.pieces.Piece;

public class AscendingPieceComparator implements PieceComparator {
    @Override
    public Comparator<Piece> getComparator() {
        return Comparator.comparingDouble(p -> p.getType().getDefaultPoint());
    }
}
