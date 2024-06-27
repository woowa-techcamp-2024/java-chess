package wootecamp.chess;

import wootecamp.chess.board.BoardPosition;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Game game = new Game();
        while(true) {
            try {
                String command = sc.nextLine();

                if (command.equals("start")) {
                    game.start();
                    continue;
                }

                if (command.startsWith("move")) {
                    move(game, command);
                    continue;
                }

                if (command.equals("end")) {
                    break;
                }

                throw new IllegalArgumentException("잘못된 입력입니다.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void move(Game game, String command) {
        String[] commands = command.split(" ");
        BoardPosition source = new BoardPosition(commands[1]);
        BoardPosition target = new BoardPosition(commands[2]);
        game.move(source, target);
    }
}
