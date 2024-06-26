import chess.Board;
import chess.ChessGame;
import chess.Command;
import chess.CommandType;
import chess.Ranks;
import view.InputView;
import view.OutputView;

public class ChessApplication {

	public static void main(String[] args) {
		Command command;
		ChessGame chessGame = null;
		do {
			command = InputView.inputCommand();
			if (command.getCommandType() == CommandType.START) {
				chessGame = new ChessGame(new Board(new Ranks()));
				OutputView.print(chessGame.board());
			} else if (command.getCommandType() == CommandType.MOVE) {
				chessGame.move(command.getArguments());
			}
		} while (command.getCommandType() != CommandType.END);
	}
}
