package chess;

import java.util.Scanner;

public class Main {

    private static final String START = "start";
    private static final String END = "end";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            String str = scanner.next();
            if(str.equals(START))
            {
                ChessGame chessGame = new ChessGame();
                chessGame.initialize();
                System.out.println(chessGame.showBoard());
            }
            else if(str.equals(END))
            {
                return;
            }
        }
    }
}
