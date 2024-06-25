package chess;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static final Board board = new Board();

    public static void main(String[] args) {
        board.initialize();

        while(true) {
            String operator = scanner.next();
            if (operator.equals("start")) System.out.println(board.print());
            if (operator.equals("end")) return;
        }
    }
}
