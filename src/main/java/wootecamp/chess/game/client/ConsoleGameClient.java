package wootecamp.chess.game.client;

import wootecamp.chess.board.Board;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.game.Game;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleGameClient {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Game game;

    public static void main(String[] args) {
        Board board = new Board();
        game = new Game(board);

        while (!game.isEnded()) {
            String request = SCANNER.nextLine().trim();
            handleRequest(request);
        }
    }

    private static void handleRequest(final String request) {
        try {
            executeCommand(request);
        } catch (IllegalArgumentException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    private static void executeCommand(final String request) {
        if (request.equals("start")) {
            game.start();
            System.out.println(game.showBoard());
            return;
        }
        if (request.startsWith("pick")) {
            pick(request);
            return;
        }

        if (request.startsWith("move")) {
            move(request);
            return;
        }

        if (request.equals("end")) {
            game.end();
            return;
        }
        throw new IllegalArgumentException(request + "는 알 수 없는 커맨드입니다.");
    }

    private static void pick(final String request) {
        String[] command = request.split(" ");

        if (command.length != 2) {
            throw new IllegalArgumentException("잘못된 이동 명령입니다.");
        }

        BoardPosition source = new BoardPosition(command[1]);
        String movablePositionsMessage = game.pick(source).stream()
                .map(BoardPosition::showPosition).collect(Collectors.joining(", "));
        System.out.println(
                "해당 기물은 다음 위치로 이동할 수 있습니다 - " + movablePositionsMessage
        );
    }

    private static void move(final String request) {
        String[] command = request.split(" ");

        if (command.length != 2) {
            throw new IllegalArgumentException("잘못된 이동 명령입니다.");
        }

        BoardPosition destination = new BoardPosition(command[1]);
        game.move(destination);

        System.out.println(game.showBoard());
    }
}
