package view;

import chess.Board;

public class OutputView {

	private OutputView() {
	}

	public static void print(Board board) {
		System.out.println(board);
	}
}
