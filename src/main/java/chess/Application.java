package chess;

import java.util.Arrays;
import java.util.Scanner;

public class Application {

    public enum GameControlCommand {
        START, END;


        public static GameControlCommand valueOfIgnoreCase(String command) {
            return Arrays.stream(GameControlCommand.values())
                    .filter(c -> c.name().toLowerCase().equals(command))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 command 입니다."));
        }

    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continueRunning = true;
            while (continueRunning) {
                System.out.print("start/end 입력: ");
                String input = scanner.nextLine();
                GameControlCommand command;
                try {
                    command = GameControlCommand.valueOfIgnoreCase(input);
                } catch (IllegalArgumentException exception) {
                    System.out.println(exception.getMessage());
                    continue;
                }
                continueRunning = execute(command);
            }
        }

    }

    private static boolean execute(GameControlCommand command) {
        return switch (command) {
            case START -> {
                startGame();
                yield true;
            }
            case END -> false;
        };
    }

    private static void startGame() {
        Board board = new Board();
        board.initialize();
        System.out.println(board.print());
    }

}
