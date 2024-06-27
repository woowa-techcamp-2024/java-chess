package wootecamp.chess;

import wootecamp.chess.board.Board;

import java.util.Scanner;

public class Main {
    private static Board board = new Board();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            String command = sc.nextLine();

            if(command.equals("start")) {
                start();
                continue;
            }

            if(command.startsWith("move")) {
                move(command);
                continue;
            }

            if(command.equals("end")) {
                break;
            }

            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private static void start() {
        board.initialize();
        System.out.println(board.showBoard());
    }

    private static void move(String command) {
        String[] commands = command.split(" ");
        final String source = commands[1];
        final String target = commands[2];
        board.move(source, target);

        System.out.println(board.showBoard());
    }
}
