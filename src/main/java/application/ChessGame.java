package application;

import chess.Board;
import chess.pieces.enums.Color;
import chess.pieces.enums.Type;
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
        var data = board.getLocationsAndPiecesByColor(color);
        var score = data.stream().filter(entry -> !Type.PAWN.isInstance(entry.getValue()))
            .mapToDouble(entry -> entry.getValue().getScore()).sum();
        score += calculatePawnScore(color);
        return score;
    }

    private double calculatePawnScore(Color color) {
        return board.getLocationsAndPiecesByColorAndType(color, Type.PAWN).stream()
            .collect(Collectors.toMap(
                entry -> entry.getKey().getY(),
                entry -> entry.getValue().getScore(),
                Double::sum))
            .values().stream()
            .mapToDouble(score -> score <= 1 ? 1 : score / 2)
            .sum();
    }

}
