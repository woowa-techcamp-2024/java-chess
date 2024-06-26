package org.example.chess.board.sort;

public class PieceComparatorFactory {
    public static final PieceComparator ASCENDING = new AscendingPieceComparator();
    public static final PieceComparator DESCENDING = new DescendingPieceComparator();
}

