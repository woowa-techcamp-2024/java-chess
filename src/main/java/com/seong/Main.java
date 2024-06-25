package com.seong;

import com.seong.chess.Board;
import java.util.Scanner;

public class Main {

    public static final String START = "start";
    public static final String END = "end";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.next();
            Board board;
            if (command.equals(START)) {
                board = new Board();
                board.initialize();
            } else if (command.equals(END)) {
                break;
            } else {
                continue;
            }
            System.out.println(board.print());
        }
    }
}