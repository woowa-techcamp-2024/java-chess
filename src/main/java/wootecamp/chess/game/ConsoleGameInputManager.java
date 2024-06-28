package wootecamp.chess.game;

import java.util.Scanner;

public class ConsoleGameInputManager implements GameInputManager {
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public String receiveRequest() {
        System.out.println("명령어를 입력해 주세요.");
        return SCANNER.nextLine();
    }
}
