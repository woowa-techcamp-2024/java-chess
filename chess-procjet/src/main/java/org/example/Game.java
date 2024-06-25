package org.example;

import java.util.Scanner;
import org.example.chess.board.Board;

public class Game {

    private static final String START = "start";
    private static final String END = "end";

    public static void main(String[] args) {
        boolean onPlaying = true;

        while (onPlaying) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("명령어를 입력해 주세요");
            System.out.println("게임 시작 : start | 게임 종료 : end");
            String command = scanner.nextLine().toLowerCase();

            if (command.equals(START)) {
                Board board = new Board();
                board.initialize();
                board.print();
                continue;
            }

            if (command.equals(END)) {
                onPlaying = false;
            }

            System.out.println("올바른 명령어를 입력해주세요");
        }
    }
}