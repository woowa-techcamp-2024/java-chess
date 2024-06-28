package wootecamp.chess.game;

import wootecamp.chess.board.Board;

public abstract class GameFactory {
    public Game createGame() {
        GameOutputManager gameOutputManager = gameOutputManager();
        GameInputManager gameInputManager = gameInputManager();
        Board board = new Board();

        return new Game(gameInputManager, gameOutputManager, board);
    }

    protected abstract GameOutputManager gameOutputManager();
    protected abstract GameInputManager gameInputManager();
}
