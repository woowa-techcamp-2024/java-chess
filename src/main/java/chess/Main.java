package chess;

import chess.config.AppConfig;

import java.util.Scanner;

public class Main {

    private static final String start = "start";
    private static final String end = "end";

    public static void main(String[] args) {
        ChessGame chessGame = AppConfig.chessGame();

        Scanner scanner = new Scanner(System.in);

        if (!init(scanner, chessGame)) return;

        if(play(scanner, chessGame)) {
            System.out.println("체크메이트! 게임이 종료되었습니다.");
        }
    }

    private static boolean init(final Scanner scanner, final ChessGame chessGame) {
        System.out.println("게임을 시작하시고 싶다면 'start' 또는 하지 않으시려면 'end'를 입력해주세요.");

        while (true) {
            String command = scanner.nextLine().trim();
            if (command.equals(start)) {
                System.out.println("게임이 시작되었습니다.");
                chessGame.start();
                break;
            } else if (command.equals(end)) {
                System.out.println("게임이 종료되었습니다.");
                chessGame.end();
                return false;
            }

            System.out.println("잘못 입력하셨습니다. 'start' 또는 'end'를 입력해주세요.");
        }
        return true;
    }

    private static boolean play(final Scanner scanner, final ChessGame chessGame) {
        while (true) {
            System.out.println("이동하고 싶은 말의 위치와 이동할 위치를 입력해주세요. ex) move a1 a2");
            String[] command = splitCommand(scanner);
            chessGame.play(command[0], command[1]);
            if (chessGame.isCheckmate()) {
                return false;
            }
        }
    }

    private static String[] splitCommand(final Scanner scanner) {
        while (true) {
            String command = scanner.nextLine().trim();

            if (!command.startsWith("move")) {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                continue;
            }
            command = command.replaceFirst("move", "").trim();

            String[] commands = command.split(" ");

            if (commands.length != 2) {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                continue;
            }

            return commands;
        }
    }
}
