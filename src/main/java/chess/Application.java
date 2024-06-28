package chess;

import chess.board.ChessGame;
import chess.pieces.Position;
import chess.view.ChessView;
import java.util.Arrays;
import java.util.Scanner;

public class Application {

    private static ChessGame chessGame;

    private static ChessView chessView;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continueRunning = true;
            while (continueRunning) {
                System.out.print("start/end/move src dest 입력: ");
                String input = scanner.nextLine();
                GameControlCommand command = GameControlCommand.valueOfIgnoreCase(input);
                continueRunning = execute(command, input);
            }
        }

    }

    private static boolean execute(GameControlCommand command, String originalCommand) {
        return switch (command) {
            case START -> {
                startGame();
                yield true;
            }
            case MOVE -> {
                movePiece(originalCommand);
                yield true;
            }
            case END -> false;
        };
    }

    private static void startGame() {
        chessGame = new ChessGame();
        chessView = new ChessView();
        chessGame.initialize();
        chessView.print(chessGame.getBoard());
    }

    private static void movePiece(String originalCommand) {
        String[] token = originalCommand.split(" ");
        chessGame.move(Position.fromString(token[1]), Position.fromString(token[2]));
        chessView.print(chessGame.getBoard());
    }

    enum GameControlCommand {
        START, END, MOVE;


        public static GameControlCommand valueOfIgnoreCase(String command) {
            return Arrays.stream(GameControlCommand.values())
                    .filter(c -> command.startsWith(c.name().toLowerCase()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException());
        }
    }


}
