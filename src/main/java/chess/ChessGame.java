package chess;

import chess.calculator.DefaultPointCalculateStrategy;
import chess.calculator.PointCalculator;
import chess.calculator.SameFilePawnPointCalculateStrategy;
import chess.pieces.Piece.Color;
import java.util.List;

public class ChessGame {
    private final PointCalculator pointCalculator;

    public ChessGame() {
        this.pointCalculator = new PointCalculator(List.of(
                new DefaultPointCalculateStrategy(),
                new SameFilePawnPointCalculateStrategy()
        ));
    }

    public double calculatePoint(Color color) {
        return 0;
//        return pointCalculator.calculate(this, color);
    }
}
