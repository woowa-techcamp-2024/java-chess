package chess;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Board board = new Board();
    private static final ChessGame chessGame = new ChessGame(board);
    private static final ChessView chessView = new ChessView(board);

    public static void main(String[] args) {
        chessGame.start();
        while(true) {
            String operator = scanner.nextLine();
            if (operator.equals("start")) System.out.println(chessView.showBoard());
            if (operator.equals("end")) return;
            if (operator.startsWith("move")) {
                try {
                    String[] operands = operator.split(" ");
                    chessGame.move(operands[1], operands[2]);
                    System.out.println(chessView.showBoard());
                }
                catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }
    }
}
