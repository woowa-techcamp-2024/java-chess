package chess.calculator;

import chess.board.Board;
import chess.pieces.Piece.Color;
import java.util.List;

public class PointCalculator {
    private List<PointCalculateStrategy> strategies;

    public PointCalculator(List<PointCalculateStrategy> strategies) {
        this.strategies = strategies;
    }

    public double calculate(Board board, Color color) {
        return strategies.stream()
                .mapToDouble(strategy -> strategy.calculate(board, color))
                .sum();
    }
}
