
import chess.ChessGame;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static chess.ChessGame.initializeCmdToPos;

public class Main {
    private enum GameCommand {
        START("start"), END("end"), MOVE("move");

        private final String command;

        GameCommand(String command) {
            this.command = command;
        }
    }

    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        initializeCmdToPos();
        boolean isStarted = false;
        while(true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                String input = scanner.nextLine();
                if (!isStarted && Objects.equals(GameCommand.START.command, input)) {
                    isStarted = true;
                    chessGame.initialize();
                    System.out.println(chessGame.showBoard());
                }
                else if (Objects.equals(GameCommand.END.command, input)) {
                    return ;
                }
                else if (isStarted && input.startsWith(GameCommand.MOVE.command)) {
                    clearConsole();
                    String[] cmd = input.split(" ");
                    try {
                        chessGame.move(cmd[1], cmd[2]);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(chessGame.showBoard());
                }
                else {
                    System.out.println("not in else if = " + input);
                }
            }
        }
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                Runtime.getRuntime().exec("cmd /c cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
