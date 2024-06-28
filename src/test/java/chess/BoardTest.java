package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pieces.Color;
import pieces.Piece;
import pieces.PieceType;

public class BoardTest {

	private Board board;

	@BeforeEach
	void setUp() {
		board = new Board(new Ranks());
	}

	@Test
	void sizeTest() {
		assertThat(board.size()).isEqualTo(8);
	}

	@ParameterizedTest
	@CsvSource({"0,0,ROOK", "0,1,KNIGHT", "0,2,BISHOP", "0,3,QUEEN", "0,4,KING", "1,0,PAWN"})
	void getPieceByCoordinateTest(int x, int y, PieceType pieceType) {
		assertThat(board.getPieceByPosition(x, y).getPieceType()).isEqualTo(pieceType);
	}

	@ParameterizedTest
	@CsvSource({"a8,ROOK", "b8,KNIGHT", "c8,BISHOP", "d8,QUEEN", "e8,KING", "a7,PAWN"})
	void getPieceByPositionTest(String position, PieceType pieceType) {
		Position pos = Position.of(position);
		assertThat(board.getPieceByPosition(pos).getPieceType()).isEqualTo(pieceType);
	}

	@ParameterizedTest
	@ValueSource(strings = {"b8a5", "b8c8", "g8f5", "g8h7", "a6a5a8a6a6h6", "d7d4d8d6d6g3"})
	void moveTest(String s) {
		for (int i = 0; i < s.length(); i += 4) {
			Position source = Position.of(s.substring(i, i + 2));
			Position destination = Position.of(s.substring(i + 2, i + 4));
			assertThatThrownBy(() -> board.move(source, destination, 1))
				.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 1})
	void isGameEndTest(int turn) {
		int row = turn == 0 ? 7 : 0;
		board.ranks().getRanks().get(row).pieces().set(4, Piece.of(Color.BLANK, PieceType.BLANK));
		assertThat(board.isGameEnd(turn)).isTrue();
	}
}
