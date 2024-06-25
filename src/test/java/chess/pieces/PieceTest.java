package chess.pieces;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class PieceTest {
    @Test
    @DisplayName("모든 종류의 기물이 생성되어야 한다")
    public void create() {
        verifyPiece(Piece.createWhitePawn(), Colors.WHITE, Representations.WHITE_PAWN);
        verifyPiece(Piece.createWhiteKing(), Colors.WHITE, Representations.WHITE_KING);
        verifyPiece(Piece.createWhiteQueen(), Colors.WHITE, Representations.WHITE_QUEEN);
        verifyPiece(Piece.createWhiteRook(), Colors.WHITE, Representations.WHITE_ROOK);
        verifyPiece(Piece.createWhiteBishop(), Colors.WHITE, Representations.WHITE_BISHOP);
        verifyPiece(Piece.createWhiteKnight(), Colors.WHITE, Representations.WHITE_KNIGHT);

        verifyPiece(Piece.createBlackKing(), Colors.BLACK, Representations.BLACK_KING);
        verifyPiece(Piece.createBlackQueen(), Colors.BLACK, Representations.BLACK_QUEEN);
        verifyPiece(Piece.createBlackRook(), Colors.BLACK, Representations.BLACK_ROOK);
        verifyPiece(Piece.createBlackBishop(), Colors.BLACK, Representations.BLACK_BISHOP);
        verifyPiece(Piece.createBlackKnight(), Colors.BLACK, Representations.BLACK_KNIGHT);
        verifyPiece(Piece.createBlackPawn(), Colors.BLACK, Representations.BLACK_PAWN);
    }

    private void verifyPiece(final Piece piece, final Colors color, final Representations representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }
}
