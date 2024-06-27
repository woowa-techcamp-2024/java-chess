package pe.goblin.chess.domain.game;

import pe.goblin.chess.domain.board.Board;
import pe.goblin.chess.domain.board.type.BoardType;
import pe.goblin.chess.domain.game.vo.GameStatus;
import pe.goblin.chess.domain.piece.Piece;
import pe.goblin.chess.domain.piece.vo.Color;

import java.util.List;

public class Game {
    private final Board board;

    private Color turn;
    private GameStatus status;

    public Game(BoardType boardType) {
        board = new Board(boardType);
        turn = Color.BLACK;
        status = GameStatus.IN_PROGRESS;
    }

    public void move(String departure, String destination) {
        Piece piece = board.findPieceAt(departure);
        if (piece == null || !piece.isColor(turn)) {
            throw new RuntimeException("not your turn");
        }
        board.move(departure, destination);
        switchTurn();

//        if (board.isDone()) {
//            board.getScoreOf(Color.WHITE);
//            board.getScoreOf(Color.BLACK);
//            status = GameStatus.WHITE_WINS;
//        }
    }

    private void switchTurn() {
        turn = turn == Color.BLACK ? Color.WHITE : Color.BLACK;
    }

    public GameStatus getStatus() {
        return status;
    }

    public List<String> showBoard() {
        return null;
    }

    public GameScore showResult() {
        return null;
    }

    public record GameScore(int whiteScore, int blackScore) {
    }
}
