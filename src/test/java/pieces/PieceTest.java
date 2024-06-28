package pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PieceTest {

	@Test
	void isSameColorTest() {
		Piece piece = Piece.of(Color.WHITE, PieceType.PAWN);
		Piece piece1 = Piece.of(Color.WHITE, PieceType.KNIGHT);
		Piece piece2 = Piece.of(Color.BLACK, PieceType.QUEEN);

		assertThat(piece.isSameColor(piece1.getColor())).isTrue();
		assertThat(piece1.isSameColor(piece2.getColor())).isFalse();
		assertThat(piece.isSameColor(piece2.getColor())).isFalse();
	}

	@ParameterizedTest
	@CsvSource({"WHITE,PAWN,♙", "WHITE,KNIGHT,♘", "WHITE,ROOK,♖", "WHITE,BISHOP,♗", "WHITE,QUEEN,♕", "WHITE,KING,♔"})
	void getRepresentationTest(Color color, PieceType type, char result) {
		Piece piece = Piece.of(color, type);
		assertThat(piece.getRepresentation()).isEqualTo(result);
	}

	@Test
	void validateTurnTest() {
		Piece white = Piece.of(Color.WHITE, PieceType.PAWN);
		Piece black = Piece.of(Color.BLACK, PieceType.KNIGHT);

		assertThatCode(() -> {
			white.validateTurn(0);
			black.validateTurn(1);
		}).doesNotThrowAnyException();
	}
}
