package pe.goblin.chess.application;

import pe.goblin.chess.application.exception.ApplicationException;
import pe.goblin.chess.application.usecase.GameCreationUseCase;
import pe.goblin.chess.storage.GameStorage;

public class GameCreationService implements GameCreationUseCase {
    private final GameStorage gameStorage;

    public GameCreationService(GameStorage gameStorage) {
        this.gameStorage = gameStorage;
    }

    @Override
    public void chooseBoard(BoardTypeQuery query) throws ApplicationException {

    }
}
