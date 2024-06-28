package pe.goblin.chess;

import pe.goblin.chess.application.BoardQueryImpl;
import pe.goblin.chess.application.GameCreationService;
import pe.goblin.chess.application.GamePlayService;
import pe.goblin.chess.console.AppConsole;
import pe.goblin.chess.storage.GameStorage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameStorage gameStorage = new GameStorage();
        AppConsole appConsole = new AppConsole(new BoardQueryImpl(), new GameCreationService(gameStorage), new GamePlayService(gameStorage));
        run(appConsole);
    }

    private static void run(AppConsole appConsole) {
        Scanner scanner = new Scanner(System.in);

        while (!appConsole.isShutDown()) {
            System.out.println(appConsole.flush());
            appConsole.processInput(scanner.nextLine());
        }
    }
}
