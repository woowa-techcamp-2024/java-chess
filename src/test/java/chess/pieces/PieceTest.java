package chess.pieces;

import org.junit.jupiter.api.*;
;
import static chess.pieces.Piece.Type;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    @DisplayName("해당하는 색 폰이 생성되어야 한다")
    void create_piece() {
        verifyPiece(Piece.createWhitePawn(), Piece.createBlackPawn(), Type.PAWN);
        verifyPiece(Piece.createWhiteKnight(), Piece.createBlackKnight(), Type.KNIGHT);
        verifyPiece(Piece.createWhiteRook(), Piece.createBlackRook(), Type.ROOK);
        verifyPiece(Piece.createWhiteBishop(), Piece.createBlackBishop(), Type.BISHOP);
        verifyPiece(Piece.createWhiteQueen(), Piece.createBlackQueen(), Type.QUEEN);
        verifyPiece(Piece.createWhiteKing(), Piece.createBlackKing(), Type.KING);

        Piece blank = Piece.createBlank();
        assertThat(blank.isWhite()).isFalse();
        assertThat(blank.isBlack()).isFalse();

        assertThat(Type.NO_PIECE.getBlackRepresentation()).isEqualTo(blank.getType());
    }

    @Test
    @DisplayName("검정색 피스인가?")
    void isBlack() {
        Piece piece = Piece.createBlackPawn();
        assertThat(piece.isBlack()).isTrue();
    }

    @Test
    @DisplayName("흰 피스인가?")
    void isWhite() {
        Piece piece = Piece.createWhitePawn();
        assertThat(piece.isWhite()).isTrue();
    }

    @Test
    void getRepresentation() {
        assertThat("p").isEqualTo(Piece.Type.PAWN.getWhiteRepresentation());
        assertThat("P").isEqualTo(Piece.Type.PAWN.getBlackRepresentation());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type.getWhiteRepresentation(), whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type.getBlackRepresentation(), blackPiece.getType());

    }
}
