package chess.pieces;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PieceTest {

	@Test
	public void 피스_생성() {
		verifyPiece(Piece.createWhitePawn(), Piece.Color.WHITE, Piece.Type.PAWN);
		verifyPiece(Piece.createBlackPawn(), Piece.Color.BLACK, Piece.Type.PAWN);
		verifyPiece(Piece.createWhiteKnight(), Piece.Color.WHITE, Piece.Type.KNIGHT);
		verifyPiece(Piece.createBlackKnight(), Piece.Color.BLACK, Piece.Type.KNIGHT);
		verifyPiece(Piece.createWhiteBishop(), Piece.Color.WHITE, Piece.Type.BISHOP);
		verifyPiece(Piece.createBlackBishop(), Piece.Color.BLACK, Piece.Type.BISHOP);
		verifyPiece(Piece.createWhiteRook(), Piece.Color.WHITE, Piece.Type.ROOK);
		verifyPiece(Piece.createBlackRook(), Piece.Color.BLACK, Piece.Type.ROOK);
		verifyPiece(Piece.createWhiteQueen(), Piece.Color.WHITE, Piece.Type.QUEEN);
		verifyPiece(Piece.createBlackQueen(), Piece.Color.BLACK, Piece.Type.QUEEN);
		verifyPiece(Piece.createWhiteKing(), Piece.Color.WHITE, Piece.Type.KING);
		verifyPiece(Piece.createBlackKing(), Piece.Color.BLACK, Piece.Type.KING);

		Piece blank = Piece.createBlank();
		assertFalse(blank.isWhite());
		assertFalse(blank.isBlack());
		assertEquals(blank.getType(), Piece.Type.NO_PIECE);
	}

	private void verifyPiece(Piece piece, Piece.Color color, Piece.Type type) {
		assertEquals(color, piece.getColor());
		assertEquals(type, piece.getType());
	}

	@Test
	public void 색_확인() {
		Piece whitePawn = Piece.createWhitePawn();
		Piece blackPawn = Piece.createBlackPawn();
		assertThat(whitePawn.isWhite()).isTrue();
		assertThat(blackPawn.isBlack()).isTrue();
		assertThat(whitePawn.isBlack()).isFalse();
		assertThat(blackPawn.isWhite()).isFalse();
	}
}
