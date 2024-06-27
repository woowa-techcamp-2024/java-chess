package chess.calculator;

import chess.board.Board;
import chess.pieces.Piece;
import chess.pieces.Piece.Color;

public class DefaultPointCalculateStrategy implements PointCalculateStrategy {

    @Override
    public double calculate(Board board, Color color) {

        return board.getRanks().stream()
                .mapToDouble(rank -> rank.getPieces().stream()
                        .filter(piece -> piece.hasColor(color))
                        .mapToDouble(Piece::getDefaultPoint)
                        .sum())
                .sum();
    }
}
