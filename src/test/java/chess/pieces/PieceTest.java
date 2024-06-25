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
        verifyPiece(Piece.createWhite(Type.PAWN), Piece.createBlack(Type.PAWN), Type.PAWN);
        verifyPiece(Piece.createWhite(Type.KNIGHT), Piece.createBlack(Type.KNIGHT), Type.KNIGHT);
        verifyPiece(Piece.createWhite(Type.ROOK), Piece.createBlack(Type.ROOK), Type.ROOK);
        verifyPiece(Piece.createWhite(Type.BISHOP), Piece.createBlack(Type.BISHOP), Type.BISHOP);
        verifyPiece(Piece.createWhite(Type.QUEEN), Piece.createBlack(Type.QUEEN), Type.QUEEN);
        verifyPiece(Piece.createWhite(Type.KING), Piece.createBlack(Type.KING), Type.KING);

        Piece blank = Piece.createBlank();
        assertThat(blank.isWhite()).isFalse();
        assertThat(blank.isBlack()).isFalse();

        assertThat(Type.NO_PIECE.getBlackRepresentation()).isEqualTo(blank.getType());
    }

    @Test
    @DisplayName("검정색 피스인가?")
    void isBlack() {
        Piece piece = Piece.createBlack(Type.PAWN);
        assertThat(piece.isBlack()).isTrue();
    }

    @Test
    @DisplayName("흰 피스인가?")
    void isWhite() {
        Piece piece = Piece.createWhite(Type.PAWN);
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
