package pe.goblin.chess.domain.game;

import pe.goblin.chess.domain.board.Board;
import pe.goblin.chess.domain.board.type.BoardType;
import pe.goblin.chess.domain.game.vo.GameStatus;
import pe.goblin.chess.domain.piece.Piece;
import pe.goblin.chess.domain.piece.vo.Color;

public class Game {
    private final Board board;

    private Color turn;
    private GameStatus status;

    public Game(BoardType boardType) {
        board = new Board(boardType);
        turn = Color.WHITE;
        status = GameStatus.IN_PROGRESS;
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

    public GameStatus getStatus() {
        return status;
    }

    public String showBoard() {
        return board.showBoard();
    }

    public GameScore showScore() {
        return new GameScore(board.getScoreOf(Color.WHITE), board.getScoreOf(Color.BLACK));
    }

    public record GameScore(double whiteScore, double blackScore) {
    }
}
