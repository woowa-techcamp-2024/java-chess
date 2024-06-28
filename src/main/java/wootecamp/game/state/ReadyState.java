package wootecamp.game.state;

import wootecamp.game.Game;

public class ReadyState extends State {
    public ReadyState(Game game) {
        super(game);
    }

    @Override
    public void handleRequest(String request) {
        if(!request.equals("start")) {
            throw new RuntimeException("시작하지 않은 상태입니다.");
        }

        super.game.start();
        game.changeState(new PlayingState(game));
    }
}
