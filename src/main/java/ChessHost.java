import chess.board.Position;
import chess.game.ChessView;
import chess.game.Game;

import java.util.Scanner;

public class ChessHost {
    private final ChessView chessView = new ChessView();

    public void play() {
        Scanner scanner = new Scanner(System.in);
        chessView.startMessage();

        Game game = new Game();
        while (true) {
            chessView.pressKeyMessage();
            Command command = inputCommand(scanner);

            if (command == Command.START) {
                game.newGame();
            }
            if (command == Command.END) {
                chessView.showWinner(game.getWinnerByScore());
                break;
            }
            if (command == Command.MOVE) {
                Position source;
                Position target;

                try {
                    source = Position.from(scanner.next());
                    target = Position.from(scanner.next());
                } catch(IllegalArgumentException e) {
                    chessView.wrongPosMessage();
                    continue;
                }
                try {
                    game.move(source, target);
                } catch(IllegalArgumentException e) {
                    chessView.wrongMoveMessage();
                    continue;
                }
            }

            // 게임종료시 출력
            if (game.getGameState().isEnd()) {
                chessView.showWinner(game.getGameState());
                break;
            }

            // 체스판 출력
            chessView.showBoard(game.print());
        }
    }

    private Command inputCommand(Scanner scanner) {
        while (true) {
            try {
                return Command.from(scanner.next());
            } catch (IllegalArgumentException e) {
                chessView.wrongCommandMessage();
            }
        }
    }
}
