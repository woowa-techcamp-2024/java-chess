import chess.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChessGame {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while (true) {
            input = bf.readLine();
            if (input.equals("start")) {
                Board board = new Board();
                board.initialize();
                board.print();
            }
            if (input.equals("end")) {
                break;
            }
        }
    }
}
