package pe.goblin.chess.console;

import pe.goblin.chess.application.query.BoardQuery;
import pe.goblin.chess.application.query.BoardQuery.BoardTypeResponse;
import pe.goblin.chess.application.usecase.GameCreationUseCase;
import pe.goblin.chess.application.usecase.GameCreationUseCase.BoardTypeQuery;
import pe.goblin.chess.application.usecase.GamePlayUseCase;
import pe.goblin.chess.application.usecase.GamePlayUseCase.MoveResult;
import pe.goblin.chess.domain.game.vo.GameStatus;
import pe.goblin.chess.exception.ApplicationException;

import java.util.List;
import java.util.Map;

public class AppConsole {
    private static final String WELCOME_DISPLAY = "Welcome to Goblin Chess(type 'start' or 'end'): ";
    private static final String CHOOSE_BOARD_DISPLAY = "Type the number of board type to play: ";
    private static final String MOVE_PIECE_DISPLAY = "command move (ex. move a2 a3): ";
    private static final String WHITE_WIN_DISPLAY = "white wins: ";
    private static final String BLACK_WIN_DISPLAY = "black wins: ";
    private static final String INVALID_NOT_INTEGER_DISPLAY = "invalid input! type number";

    private final BoardQuery boardQuery;
    private final GameCreationUseCase gameCreationUseCase;
    private final GamePlayUseCase gamePlayUseCase;
    private final TextOutput output;

    private Processor processor;
    private boolean shutdown;

    public AppConsole(BoardQuery boardQuery, GameCreationUseCase gameCreationUseCase, GamePlayUseCase gamePlayUseCase) {
        this.boardQuery = boardQuery;
        this.gameCreationUseCase = gameCreationUseCase;
        this.gamePlayUseCase = gamePlayUseCase;
        output = new TextOutput();
        processor = getGameStartProcessor();
    }

    private Processor getGameStartProcessor() {
        output.print(WELCOME_DISPLAY);

        return input -> {
            switch (input) {
                case "start":
                    return configureBoardProcess();
                case "end":
                    return shutdownProcess();
                default:
                    output.println(input);
                    return getGameStartProcessor();
            }
        };
    }

    private Processor configureBoardProcess() {
        BoardTypeResponse boardTypeResponse = boardQuery.showPossibleBoardTypes();
        output.println(CHOOSE_BOARD_DISPLAY);
        for (Map.Entry<Integer, List<String>> possibleBoardType : boardTypeResponse.boardTypes().entrySet()) {
            output.println(" - " + possibleBoardType.getKey() + " - ");
            for (String boardRow : possibleBoardType.getValue()) {
                output.println(boardRow);
            }
        }

        return input -> {
            try {
                int typeKey = Integer.parseInt(input);
                gameCreationUseCase.chooseBoard(new BoardTypeQuery(typeKey));
                return movePieceProcess();
            } catch (ApplicationException exception) {
                output.println(exception.getMessage());
                return configureBoardProcess();
            } catch (NumberFormatException exception) {
                output.println(INVALID_NOT_INTEGER_DISPLAY);
                return configureBoardProcess();
            }
        };
    }

    private Processor movePieceProcess() {
        output.println(MOVE_PIECE_DISPLAY);

        return input -> {
            try {
                MoveResult moveResult = gamePlayUseCase.move(input);
                switch (moveResult.gameStatus()) {
                    case GameStatus.IN_PROGRESS -> {
                        output.println(moveResult.board());
                        return movePieceProcess();
                    }
                    case GameStatus.WHITE_WINS, GameStatus.BLACK_WINS -> {
                        return showResult(moveResult);
                    }
                    default -> throw new RuntimeException();
                }
            } catch (ApplicationException exception) {
                output.println(exception.getMessage());
                return movePieceProcess();
            }
        };
    }

    private Processor showResult(MoveResult moveResult) {
        switch (moveResult.gameStatus()) {
            case GameStatus.BLACK_WINS -> output.print(BLACK_WIN_DISPLAY);
            case GameStatus.WHITE_WINS -> output.print(WHITE_WIN_DISPLAY);
            default -> throw new RuntimeException();
        }
        output.println(moveResult.blackScore() + " vs " + moveResult.whiteScore());

        return getGameStartProcessor();
    }

    private Processor shutdownProcess() {
        shutdown = true;
        return null;
    }

    public String flush() {
        return output.flush();
    }

    public void processInput(String input) {
        processor = processor.run(input);
    }

    public boolean isShutDown() {
        return shutdown;
    }
}
