package wootecamp.chess;

import wootecamp.game.ConsoleGameFactory;
import wootecamp.game.Game;
import wootecamp.game.GameFactory;

public class Main {
    public static void main(String[] args) {
        GameFactory gameFactory = new ConsoleGameFactory();
        Game game = gameFactory.createGame();

        while(!game.isEnd()) {
            game.receiveRequest();
        }
    }
}
