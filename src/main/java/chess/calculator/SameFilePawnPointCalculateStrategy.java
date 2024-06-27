package chess.calculator;

import chess.board.Board;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import java.util.stream.IntStream;

public class SameFilePawnPointCalculateStrategy implements PointCalculateStrategy {
    private static final double MINUS_POINT = -0.5;

    @Override
    public double calculate(Board board, Color color) {
        return IntStream.range(0, Board.FILE_COUNT)
                .mapToDouble(fileNum -> {
                    long count = countPawn(board, fileNum, color);
                    if (count > 1) {
                        return MINUS_POINT * count;
                    }
                    return 0;
                }).sum();
    }

    private long countPawn(Board board, int fileNum, Color color) {
        return board.getRanks()
                .stream()
                .map(rank -> rank.findPiece(fileNum))
                .filter(piece -> piece.hasColor(color))
                .filter(piece -> piece.hasType(Type.PAWN))
                .count();
    }
}
