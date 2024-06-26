package chess;

import view.OutputView;

public record ChessGame(Board board) {

	public void move(String[] arguments) {
		board.move(Position.of(arguments[0]), Position.of(arguments[1]));
		OutputView.print(board);
	}

}
