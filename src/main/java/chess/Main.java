package chess;

import chess.board.Board;
import chess.view.ChessView;

import java.util.Scanner;

public class Main {

    private static final String QUIT = "q";
    private static final String PRINT = "p";
    private static final String MOVE = "move";

    private static final int MOVE_FROM_INDEX = 1;
    private static final int MOVE_TO_INDEX = 2;

    public static void main(String[] args) {
        Board board = new Board();

        Scanner sc = new Scanner(System.in);
        String input;

        while (!(input = sc.nextLine()).equals(QUIT)) {
            if(isPrintCommand(input)) {
                System.out.println(ChessView.printBoard(board));
            }
            if(isMoveCommand(input)) {
                String[] split = input.split(" ");
                // TODO : 입력 시 validation도 필요하지 않을까?
                board.move(split[MOVE_FROM_INDEX], split[MOVE_TO_INDEX]);
            }
        }
        System.out.println("종료");

    }

    private static boolean isPrintCommand(String input) {
        return input.equals(PRINT);
    }

    private static boolean isMoveCommand(String input) {
        return input.startsWith(MOVE);
    }
}
