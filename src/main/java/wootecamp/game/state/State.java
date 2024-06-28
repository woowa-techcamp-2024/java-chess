package wootecamp.game.state;

import wootecamp.chess.board.Board;
import wootecamp.game.Game;

public abstract class State {
    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public abstract void handleRequest(String request);
}
