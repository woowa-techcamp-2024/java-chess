package wootecamp.game;

import wootecamp.chess.board.Board;

public interface GameOutputManager {
    void showBoard(Board board);
    void showError(String message);
}
