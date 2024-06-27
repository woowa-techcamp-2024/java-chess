package chess.config;

import chess.ChessGame;
import chess.Rule;
import chess.board.Board;

public class AppConfig {

    public static ChessGame chessGame() {
        Board board = board();
        return new ChessGame(board, new Rule(board));
    }

    public static Rule rule(final Board board) {
        return new Rule(board);
    }

    public static Board board() {
        return new Board();
    }

}
