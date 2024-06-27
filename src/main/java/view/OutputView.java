package view;

import chess.Board;
import pieces.Color;

public class OutputView {

	private OutputView() {
	}

	public static void print(Board board) {
		System.out.println(board);
	}

	public static void printString(String s) {
		System.out.println(s);
	}

	public static void printPromotion() {
		System.out.println("you can do promotion. select type in [BISHOP, ROOK, KNIGHT, QUEEN]");
	}

	public static void printEnd(int turn) {
		System.out.println("game end. " + Color.fromByTurn(1 - turn).name() + " player win.");
	}
}
