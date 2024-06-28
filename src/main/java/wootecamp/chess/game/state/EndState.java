package wootecamp.chess.game.state;

import wootecamp.chess.game.Game;

public class EndState extends State {
    public EndState(Game game) {
        super(game);
    }

    @Override
    public void handleRequest(String request) {
        throw new RuntimeException("게임이 끝났습니다.");
    }
}
