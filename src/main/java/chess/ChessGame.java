package chess;

import java.util.Scanner;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public ChessGame() {
        this(new Board());
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine();

            if (command.equalsIgnoreCase("start")) {
                board.initialize();
                board.print();
            } else if (command.equalsIgnoreCase("end")) {
                System.out.println("게임을 종료합니다.");
                break;
            } else {
                System.out.println("유효하지 않은 명령어입니다");
            }
        }

        scanner.close();
    }
}
