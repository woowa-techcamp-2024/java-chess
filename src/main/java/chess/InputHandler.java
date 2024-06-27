package chess;

import java.util.Scanner;

public class InputHandler {
    private ChessGame chessGame;
    private Scanner scanner;

    public InputHandler(ChessGame chessGame) {
        this.chessGame = chessGame;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        String command;
        while (true) {
            System.out.print("> ");
            command = scanner.nextLine();

            try {
                if (command.equalsIgnoreCase("start")) {
                    if (chessGame.isStarted()) {
                        System.out.println("이미 시작된 게임입니다.");
                        continue;
                    }
                    chessGame.start();
                } else if (command.equalsIgnoreCase("end")) {
                    System.out.println("게임을 종료합니다.");
                    break;
                } else if (command.startsWith("move")) {
                    if (!chessGame.isStarted()) {
                        System.out.println("게임이 시작되지 않았습니다.");
                        continue;
                    }
                    String[] commands = command.split(" ");
                    if (commands.length == 2) {
                        String source = commands[1];
                        System.out.println(chessGame.getMovablePoints(source));
                        continue;
                    } else if (commands.length != 3) {
                        System.out.println("유효하지 않은 명령어입니다");
                        continue;
                    }
                    String source = commands[1];
                    String target = commands[2];
                    chessGame.move(source, target);
                } else {
                    System.out.println("유효하지 않은 명령어입니다");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            if (!chessGame.isStarted()) continue;

            if (chessGame.isCheck()) {
                System.out.println("체크!");
            }
            chessGame.print();
            if (chessGame.isEnded()) {
                System.out.println("게임이 종료되었습니다.");
                break;
            }
        }
    }

}
