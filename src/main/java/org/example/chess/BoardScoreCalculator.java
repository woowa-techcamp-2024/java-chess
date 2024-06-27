package org.example.chess;

import java.util.List;
import org.example.chess.Board.Column;
import org.example.pieces.Piece;
import org.example.pieces.Piece.Color;

public class ChessScoreCalculator {
    private ChessScoreCalculator () {
    }

    public double calculatePoint(Board board, Color color) {
        List<Column> columns = board.getColumns();
        return columns.stream()
            .mapToDouble(column -> calculateColumnPoints(column, color))
            .sum();
    }


    private double calculateColumnPoints(Column column, Color color) {
        double pawnPoints = calculatePawnPoints(column, color);
        double otherPoints = calculateOtherPoints(column, color);
        return pawnPoints + otherPoints;
    }


    private double calculatePawnPoints(Column column, Color color) {
        long pawnCount = column.getPieces().stream()
            .filter(piece -> piece.isSameColor(color) && piece.isPawn())
            .count();

        return pawnCount <= 1 ? pawnCount : pawnCount * 0.5;
    }

    private double calculateOtherPoints(Column column, Color color) {
        return column.getPieces().stream()
            .filter(piece -> piece.isSameColor(color) && !piece.isPawn())
            .mapToDouble(Piece::getPoint)
            .sum();
    }
}
