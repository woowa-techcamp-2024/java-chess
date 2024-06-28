package wootecamp.game.state;

import wootecamp.chess.board.BoardPosition;
import wootecamp.game.Game;

public class PlayingState extends State {
    public PlayingState(Game game) {
        super(game);
    }

    @Override
    public void handleRequest(String request) {
        if(request.equals("end")) {
            game.changeState(new EndState(game));
        }
        if(!request.startsWith("move")) {
            throw new RuntimeException("이동 명령을 내려야 합니다.");
        }
        String[] commands = request.split(" ");
        BoardPosition source = new BoardPosition(commands[1]);
        BoardPosition target = new BoardPosition(commands[2]);
        game.move(source, target);
    }
}
