package wootecamp.chess.game.state;

import wootecamp.chess.game.Game;

public abstract class State {
    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public abstract void handleRequest(String request);
}
