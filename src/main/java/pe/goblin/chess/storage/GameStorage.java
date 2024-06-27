package pe.goblin.chess.storage;

import pe.goblin.chess.domain.game.Game;

public class GameStorage {
    private final ThreadLocal<Game> storage = new ThreadLocal<>();

    public void store(Game game) {
        storage.set(game);
    }

    public Game get() {
        return storage.get();
    }
}
