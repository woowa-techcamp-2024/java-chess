package chess;

import view.OutputView;

/**
 * turn 0부터 toggle되며, 0인 경우 Color.WHITE, 1인 경우 Color.BLACK
 */
public record ChessGame(Board board) {

	private static int turn = 0;

	public void move(String[] arguments) {
		board.move(Position.of(arguments[0]), Position.of(arguments[1]), turn);
		OutputView.print(board);
		toggleTurn();
	}

	public boolean isGameEnd() {
		boolean gameEnd = board.isGameEnd(turn);
		if (gameEnd) {
			OutputView.printEnd(turn);
		}
		return gameEnd;
	}

	private void toggleTurn() {
		turn = 1 - turn;
	}

}
