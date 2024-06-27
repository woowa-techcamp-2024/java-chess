package pe.goblin.chess.application.usecase;

import pe.goblin.chess.application.exception.ApplicationException;

public interface GameCreationUseCase {
    void chooseBoard(BoardTypeQuery query) throws ApplicationException;

    record BoardTypeQuery(int typeKey) {
    }
}
