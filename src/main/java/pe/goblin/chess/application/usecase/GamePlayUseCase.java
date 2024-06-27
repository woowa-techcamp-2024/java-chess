package pe.goblin.chess.application.usecase;

import pe.goblin.chess.application.exception.ApplicationException;
import pe.goblin.chess.domain.game.vo.GameStatus;

public interface GamePlayUseCase {
    GameResult move(String input) throws ApplicationException;

    record GameResult(GameStatus gameStatus, Integer whiteScore, Integer blackScore) {
    }
}
