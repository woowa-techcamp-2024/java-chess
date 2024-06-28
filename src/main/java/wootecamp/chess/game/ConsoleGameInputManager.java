package wootecamp.chess.game;

import java.util.Scanner;

public class ConsoleGameInputManager implements GameInputManager {
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public String receiveRequest() {
        return SCANNER.nextLine();
    }
}
