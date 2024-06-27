package pe.goblin.chess.application.usecase;

import pe.goblin.chess.domain.game.vo.GameStatus;
import pe.goblin.chess.exception.ApplicationException;

public interface GamePlayUseCase {
    MoveResult move(String input) throws ApplicationException;

    record MoveResult(GameStatus gameStatus, double whiteScore, double blackScore, String board) {
    }
}
