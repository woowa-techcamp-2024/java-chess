package wootecamp.chess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner sc = new Scanner(System.in);

        while(true) {
            String command = sc.next();

            if(command.equals("start")) {
                start(board);
                continue;
            }
            if(command.equals("end")) {
                break;
            }

            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private static void start(Board board) {
        board.initialize();
        System.out.println(board.showBoard());
    }

}
