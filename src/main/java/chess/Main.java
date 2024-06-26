package chess;

import chess.board.Board;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        Scanner sc = new Scanner(System.in);
        String input;

        while (!(input = sc.nextLine()).equals("q")) {
            if(input.equals("p")) {
                System.out.println(board.showBoard());
            }
        }
        System.out.println("종료");

    }
}
