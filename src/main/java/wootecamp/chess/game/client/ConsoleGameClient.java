package wootecamp.chess.game.client;

import wootecamp.chess.board.Board;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.game.Game;

import java.util.Scanner;

public class ConsoleGameClient {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Game game;

    public static void main(String[] args) {
        Board board = new Board();
        Game game = new Game(board);

        while (!game.isEnded()) {
            try {
                String request = SCANNER.nextLine().trim();
                handleRequest(request);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void handleRequest(final String request) {
        if (request.equals("start")) {
            game.start();
        }
        if (request.startsWith("move")) {
            move(request);
        }
        if (request.equals("end")) {
            game.end();
        }
        throw new IllegalArgumentException(request + "는 알 수 없는 요청입니다.");
    }

    private static void move(final String request) {
        String[] command = request.split(" ");

        BoardPosition source = new BoardPosition(command[1]);
        BoardPosition destination = new BoardPosition(command[2]);
        game.move(source, destination);
    }
}
