package pe.goblin.chess.domain.game;

import pe.goblin.chess.domain.board.Board;
import pe.goblin.chess.domain.board.ScoreEvaluator;
import pe.goblin.chess.domain.board.type.BoardType;
import pe.goblin.chess.domain.game.vo.GameStatus;
import pe.goblin.chess.domain.piece.Piece;
import pe.goblin.chess.domain.piece.vo.Color;

import java.util.List;

public class Game {
    private final Board board;
    private final ScoreEvaluator scoreEvaluator;

    private Color turn;
    private GameStatus status;

    public Game(BoardType boardType, ScoreEvaluator scoreEvaluator) {
        this.board = new Board(boardType);
        this.scoreEvaluator = scoreEvaluator;
        this.turn = Color.WHITE;
        this.status = GameStatus.IN_PROGRESS;
    }

    public void move(String departure, String destination) {
        validateAuthority(departure);
        board.move(departure, destination);
        switchTurn();
        // TODO: 게임이 끝났을 때 status 변경
    }

    private void validateAuthority(String departure) {
        Piece piece = board.findPieceAt(departure);
        if (piece == null || !piece.isColor(turn)) {
            throw new RuntimeException("not your piece!");
        }
    }

    private void switchTurn() {
        turn = turn == Color.BLACK ? Color.WHITE : Color.BLACK;
    }

    public String showBoard() {
        List<List<Piece>> pieces = board.getPieces();
        StringBuilder sb = new StringBuilder();
        for (List<Piece> row : pieces) {
            for (Piece piece : row) {
                sb.append(piece.getRepresentation());
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public GameScore showScore() {
        return new GameScore(getScoreOf(Color.WHITE), getScoreOf(Color.BLACK));
    }

    private double getScoreOf(Color color) {
        return scoreEvaluator.evaluate(color, board);
    }

    public GameStatus getStatus() {
        return status;
    }

    public record GameScore(double whiteScore, double blackScore) {
    }
}
