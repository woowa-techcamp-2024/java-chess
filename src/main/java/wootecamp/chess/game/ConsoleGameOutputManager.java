package wootecamp.chess.game;

import wootecamp.chess.board.Board;

public class ConsoleGameOutputManager implements GameOutputManager {
    @Override
    public void showBoard(Board board) {
        System.out.println(board.showBoard());
    }

    @Override
    public void showError(String message) {
        System.out.println("ERROR: " + message);
    }
}
