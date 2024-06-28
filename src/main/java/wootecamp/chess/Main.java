package wootecamp.chess;

import wootecamp.chess.game.ConsoleGameFactory;
import wootecamp.chess.game.Game;
import wootecamp.chess.game.GameFactory;

public class Main {
    public static void main(String[] args) {
        GameFactory gameFactory = new ConsoleGameFactory();
        Game game = gameFactory.createGame();

        while(!game.isEnd()) {
            game.receiveRequest();
        }
    }
}
