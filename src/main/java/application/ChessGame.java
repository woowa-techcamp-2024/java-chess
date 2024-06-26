package application;

import chess.Board;
import chess.pieces.Piece;
import chess.pieces.enums.Color;
import chess.pieces.enums.Type;
import chess.pieces.values.Location;
import java.util.stream.Collectors;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    /**
     * 현재 게임의 점수를 계산합니다.
     *
     * @param color 색상
     * @return 현재 게임의 점수
     */
    public double calculateScoreByColor(Color color) {
        var score = board.getPiecesByColor(color).stream()
            .filter(piece -> !Type.PAWN.isInstance(piece))
            .mapToDouble(Piece::getScore).sum();
        score += calculatePawnScore(color);
        return score;
    }

    private double calculatePawnScore(Color color) {
        return board.getPawnsLocationsByColor(color).stream()
            .collect(Collectors.toMap(
                Location::getCurrentY,
                entry -> 1.0,
                Double::sum))
            .values().stream()
            .mapToDouble(score -> score <= 1 ? 1 : score / 2)
            .sum();
    }

}
