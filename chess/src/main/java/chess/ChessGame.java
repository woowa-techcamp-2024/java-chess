package chess;

import java.util.Scanner;

public class ChessGame {

    private static final String START = "start";
    private static final String END = "end";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            String str = scanner.next();
            if(str.equals(START))
            {
                Board board = new Board();
                board.initialize();
                System.out.println(board.show());
            }
            else if(str.equals(END))
            {
                return;
            }
        }
    }
}
