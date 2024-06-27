package chess.config;

import chess.ChessGame;
import chess.board.Board;

public class AppConfig {

    public static ChessGame chessGame() {
        Board board = board();
        return new ChessGame(board);
    }

    public static Board board() {
        return new Board();
    }
}
