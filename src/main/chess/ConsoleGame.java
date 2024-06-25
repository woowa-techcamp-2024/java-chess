package chess;

import java.util.Scanner;

public class ConsoleGame {

    public static void main(String[] args) {
        Game game = new Game();
        Scanner sc = new Scanner(System.in);

        gameLoop:
        while (true) {
            String input = sc.nextLine();
            switch (input) {
                case "start":
                    game.initialize();
                    game.print();
                    break;
                case "end":
                    break gameLoop;
            }
        }
    }
}
