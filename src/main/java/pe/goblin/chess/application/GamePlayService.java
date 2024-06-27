package pe.goblin.chess.application;

import pe.goblin.chess.application.exception.ApplicationException;
import pe.goblin.chess.application.usecase.GamePlayUseCase;
import pe.goblin.chess.storage.GameStorage;

public class GamePlayService implements GamePlayUseCase {
    private final GameStorage gameStorage;

    public GamePlayService(GameStorage gameStorage) {
        this.gameStorage = gameStorage;
    }

    @Override
    public GameResult move(String input) throws ApplicationException {
        return null;
    }
}
