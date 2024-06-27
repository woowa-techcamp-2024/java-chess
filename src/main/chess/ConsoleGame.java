package chess;

import java.util.Scanner;
import java.util.StringTokenizer;

public class ConsoleGame {

    public static void main(String[] args) {
        Game game = new Game();
        Scanner sc = new Scanner(System.in);

        game.initialize();

        gameLoop:
        while (true) {
            game.print();

            String input = sc.nextLine();
            StringTokenizer st = new StringTokenizer(input);
            switch (st.nextToken()) {
                case "restart":
                    game.initialize();
                    break;
                case "move":
                    try {
                        Position from = Position.of(st.nextToken());
                        Position to = Position.of(st.nextToken());
                        game.move(from, to);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue gameLoop;
                    }
                    break;
                case "end":
                    break gameLoop;
            }
        }
    }
}
