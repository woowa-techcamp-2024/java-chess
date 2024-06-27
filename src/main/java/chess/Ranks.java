package chess;

import static chess.Board.*;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.IntStream;
import pieces.Color;
import pieces.Piece;
import pieces.PieceType;

public class Ranks {

	private final List<Rank> ranks = new ArrayList<>();

	public Ranks() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			List<Piece> pieces = new ArrayList<>();
			for (int column = 0; column < BOARD_SIZE; column++) {
				pieces.add(Piece.of(Color.from(row), PieceType.from(row, column)));
			}
			ranks.add(new Rank(pieces));
		}
	}

	public List<Rank> getRanks() {
		return ranks;
	}
}
