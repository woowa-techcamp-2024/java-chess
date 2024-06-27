package chess;

import chess.board.Board;
import chess.board.PointCalculator;
import chess.view.ChessView;

import java.util.Scanner;

public class Main {

    private static final String QUIT = "q";
    private static final String PRINT = "p";
    private static final String MOVE = "move";

    private static final int MOVE_FROM_INDEX = 1;
    private static final int MOVE_TO_INDEX = 2;

    public static void main(String[] args) {
        PointCalculator pointCalculator = new PointCalculator();
        Board board = new Board();
        ChessView chessView = new ChessView();
        ChessGame chessGame = new ChessGame(board, pointCalculator, chessView);

        Scanner sc = new Scanner(System.in);
        String input;

        while (!(input = sc.nextLine()).equals(QUIT)) {
            if(isPrintCommand(input)) {
                System.out.println(chessGame.printBoard());
            }
            else if(isMoveCommand(input)) {
                String[] split = input.split(" ");
                try {
                    if(split.length != 3) {
                        throw new IllegalArgumentException("명령어 형식이 잘못되었습니다.");
                    }
                    chessGame.move(split[MOVE_FROM_INDEX], split[MOVE_TO_INDEX]);
                    System.out.println(chessGame.printBoard());
                }
                catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
            else {
                System.err.println("잘못된 명령어입니다.");
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
