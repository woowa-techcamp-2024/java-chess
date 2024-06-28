package org.example.chess.board.sort;

import java.util.Comparator;
import org.example.chess.pieces.Piece;

@FunctionalInterface
public interface PieceComparator {
    Comparator<Piece> getComparator();
}
