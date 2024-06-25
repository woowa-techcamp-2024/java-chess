package chess.config;

import chess.Board;
import chess.ChessGame;

public class AppConfig {

    public static ChessGame chessGame() {
        return new ChessGame(board());
    }

    public static Board board() {
        return new Board();
    }
}
