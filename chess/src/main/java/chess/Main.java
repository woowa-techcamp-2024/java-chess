package chess;

import exception.InvalidMoveException;
import exception.OutOfBoardException;

import java.util.Scanner;

public class Main {

    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessGame chessGame = new ChessGame();
        while (true) {
            String str = scanner.next();
            if (str.equals(START)) {
                chessGame.initialize();
            } else if (str.equals(END)) {
                return;
            } else if (str.startsWith(MOVE)) {
                try {
                    chessGame.move(scanner.next(), scanner.next());
                } catch (InvalidMoveException | OutOfBoardException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(chessGame.showBoard());
        }
    }
}
