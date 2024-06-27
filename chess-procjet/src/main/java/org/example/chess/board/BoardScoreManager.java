package org.example.chess.board;

import static org.example.chess.board.Board.BOARD_SIZE;

import java.util.ArrayList;
import java.util.List;
import org.example.chess.board.Board.Rank;
import org.example.chess.board.sort.PieceComparator;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.Piece.Color;

public class BoardScoreManager {

    private static final double PAWN_SCORE_DECREMENT = 0.5;

    private final Board board;

    public BoardScoreManager(Board board) {
        this.board = board;
    }

    public double calculatePoint(Board board, Color color) {
        double points = 0.0;
        for (Rank rank : board.getBoard()) {
            points += rank.calculateRankPoint(color);
        }

        int totalInColumnPawnCount = countPawnsInColumnsByColor(board, color);
        return points - PAWN_SCORE_DECREMENT * totalInColumnPawnCount;
    }

    private int countPawnsInColumnsByColor(Board board, Color color) {
        int totalInColumnPawnCount = 0;
        for (int c = 0; c < BOARD_SIZE; c++) {
            int columnCount = 0;
            for (int r = 0; r < BOARD_SIZE; r++) {
                Piece piece = board.getBoard().get(r).getPieces().get(c);
                if (piece.isPawn() && piece.getColor() == color) {
                    columnCount++;
                }
            }
            if (columnCount > 1) {
                totalInColumnPawnCount += columnCount;
            }
        }
        return totalInColumnPawnCount;
    }

    public List<Piece> findAllPiecesSortByPoint(Board board, Color color, PieceComparator comparator) {
        List<Piece> pieces = new ArrayList<>();

        for (Rank rank : board.getBoard()) {
            pieces.addAll(rank.findPieces(color));
        }

        pieces.sort(comparator.getComparator());

        return pieces;
    }
}
