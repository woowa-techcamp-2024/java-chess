package chess.calculator;

import chess.board.Board;
import chess.pieces.Piece.Color;

public interface PointCalculateStrategy {
    double calculate(Board board, Color color);
}
