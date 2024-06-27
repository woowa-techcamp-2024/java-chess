package chess;

import pieces.Piece;

/**
 * turn 0부터 toggle되며, 0인 경우 Color.WHITE, 1인 경우 Color.BLACK
 */
public record ChessGame(Board board) {

	private static int turn = 0;

	public void move(String[] arguments) {
		board.move(Position.of(arguments[0]), Position.of(arguments[1]), turn);
		toggleTurn();
	}

	public boolean isGameEnd() {
		return board.isGameEnd(turn);
	}

	public void toggleTurn() {
		turn = 1 - turn;
	}

	public int getTurn() {
		return turn;
	}

	public Piece[][] getPiecesArray() {
		return board.ranks().getRanks().stream()
			.map(rank -> rank.pieces().toArray(Piece[]::new))
			.toArray(Piece[][]::new);
	}
}
