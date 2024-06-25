package chess;

import chess.config.AppConfig;

import java.util.Scanner;

public class Main {

    private static final String start = "start";
    private static final String end = "end";

    public static void main(String[] args) {
        ChessGame chessGame = AppConfig.chessGame();

        Scanner scanner = new Scanner(System.in);

        System.out.println("게임을 시작하시고 싶다면 'start' 또는 하지 않으시려면 'end'를 입력해주세요.");

        while (true) {
            String commend = scanner.nextLine().trim();
            if (commend.equals(start)) {
                System.out.println("게임이 시작되었습니다.");
                chessGame.start();
                break;
            } else if(commend.equals(end)) {
                System.out.println("게임이 종료되었습니다.");
                chessGame.end();
                return;
            }
            System.out.println("잘못 입력하셨습니다. 'start' 또는 'end'를 입력해주세요.");
        }
    }
}
