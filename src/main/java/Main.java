
import chess.ChessGame;

import java.util.Objects;
import java.util.Scanner;

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
                    String[] cmd = input.split(" ");
                    chessGame.move(cmd[1], cmd[2]);
                    System.out.println(chessGame.showBoard());
                }
                else {
                    System.out.println("not in else if = " + input);
                }
            }
        }
    }
}
