package wootecamp.chess.game;

public class ConsoleGameFactory extends GameFactory {

    @Override
    protected GameOutputManager gameOutputManager() {
        return new ConsoleGameOutputManager();
    }

    @Override
    protected GameInputManager gameInputManager() {
        return new ConsoleGameInputManager();
    }
}
