package chess;

import chess.board.Board;
import chess.pieces.Position;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
    private static Board board;

    enum GameControlCommand {
        START, END, MOVE;


        public static GameControlCommand valueOfIgnoreCase(String command) {
            return Arrays.stream(GameControlCommand.values())
                    .filter(c -> command.startsWith(c.name().toLowerCase()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 command 입니다."));
        }

    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continueRunning = true;
            while (continueRunning) {
                System.out.print("start/end/move src dest 입력: ");
                String input = scanner.nextLine();
                GameControlCommand command = getGameControlCommand(input);
                if (command == null) {
                    continue;
                }
                continueRunning = execute(command, input);
            }
        }

    }

    private static GameControlCommand getGameControlCommand(String input) {
        GameControlCommand command;
        try {
            command = GameControlCommand.valueOfIgnoreCase(input);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        return command;
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
        board = new Board();
        board.initialize();
        board.print();
    }

    private static void movePiece(String originalCommand) {
        String[] token = originalCommand.split(" ");
        board.move(Position.fromString(token[1]), Position.fromString(token[2]));
        board.print();
    }


}
