package chess;

import static chess.Board.BOARD_SIZE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import pieces.Color;
import pieces.Piece;
import pieces.PieceType;

public class Ranks {

	private final List<Rank> ranks;

	public Ranks() {
		ranks = IntStream.range(0, BOARD_SIZE)
			.mapToObj(row -> new Rank(new ArrayList<>(IntStream.range(0, BOARD_SIZE)
				.mapToObj(column -> Piece.of(Color.from(row), PieceType.from(row, column)))
				.toList())))
			.toList();
	}

	public List<Rank> getRanks() {
		return ranks;
	}
}
