package pe.goblin.chess.application;

import pe.goblin.chess.application.usecase.GamePlayUseCase;
import pe.goblin.chess.domain.game.Game;
import pe.goblin.chess.domain.game.Game.GameScore;
import pe.goblin.chess.domain.game.vo.GameStatus;
import pe.goblin.chess.exception.ApplicationException;
import pe.goblin.chess.storage.GameStorage;

import java.util.regex.Pattern;

public class GamePlayService implements GamePlayUseCase {
    private static final Pattern commandPattern = Pattern.compile("^move\\\\s+\\\\w+\\\\s+\\\\w+$");

    private final GameStorage gameStorage;

    public GamePlayService(GameStorage gameStorage) {
        this.gameStorage = gameStorage;
    }

    @Override
    public MoveResult move(String input) throws ApplicationException {
        validateInput(input);
        Game game = findGame();
        try {
            String[] command = input.split(" ");
            game.move(command[1], command[2]);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
        GameStatus status = game.getStatus();
        String board = getBoard(game);
        GameScore result = game.showResult();
        return new MoveResult(status, result.whiteScore(), result.blackScore(), board);
    }

    private Game findGame() throws ApplicationException {
        Game game = gameStorage.get();
        if (game == null) {
            throw new ApplicationException("Game has not been started");
        }
        return game;
    }

    private String getBoard(Game game) {
        StringBuilder sb = new StringBuilder();
        for (String row : game.showBoard()) {
            sb.append(row).append(System.lineSeparator());
        }
        return sb.toString();
    }

    private static void validateInput(String input) throws ApplicationException {
        boolean isValid = commandPattern.matcher(input).find();
        if (!isValid) {
            throw new ApplicationException("Invalid command!");
        }
    }
}
