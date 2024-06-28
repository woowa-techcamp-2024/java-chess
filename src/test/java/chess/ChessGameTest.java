package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

	private ChessGame chessGame;

	@BeforeEach
	void setUp() {
		chessGame = new ChessGame(new Board(new Ranks()));
	}

	@Test
	void toggleTurnTest() {
		chessGame.toggleTurn();
		assertThat(chessGame.getTurn()).isEqualTo(1);
	}
}
