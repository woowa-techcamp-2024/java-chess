package chess;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continueRunning = true;
            while (continueRunning) {
                System.out.print("start/end 입력: ");
                String input = scanner.nextLine();
                continueRunning = execute(input);
            }
        }

    }

    private static boolean execute(String input) {
        switch (input) {
            case "start": {
                startGame();
                return true;
            }
            case "end": {
                return false;
            }
            default: {
                return true;
            }
        }
    }

    private static void startGame() {
        Board board = new Board();
        board.initialize();
        System.out.println(board.print());
    }

}
