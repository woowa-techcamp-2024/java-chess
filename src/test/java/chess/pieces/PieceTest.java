package chess.pieces;

import org.junit.jupiter.api.*;

import static chess.pieces.Piece.Color;
import static chess.pieces.Piece.Type;

import static org.assertj.core.api.Assertions.*;

class PieceTest {

    @Test
    @DisplayName("해당하는 색 폰이 생성되어야 한다")
    void create_piece() {
        verifyPiece(Piece.createWhitePawn(), Color.WHITE.name(), Type.PAWN.getWhiteRepresentation());
        verifyPiece(Piece.createBlackPawn(), Color.BLACK.name(), Type.PAWN.getBlackRepresentation());

        verifyPiece(Piece.createWhiteBishop(), Color.WHITE.name(), Type.BISHOP.getWhiteRepresentation());
        verifyPiece(Piece.createBlackBishop(), Color.BLACK.name(), Type.BISHOP.getBlackRepresentation());

        verifyPiece(Piece.createWhiteKing(), Color.WHITE.name(), Type.KING.getWhiteRepresentation());
        verifyPiece(Piece.createBlackKing(), Color.BLACK.name(), Type.KING.getBlackRepresentation());

        verifyPiece(Piece.createWhiteQueen(), Color.WHITE.name(), Type.QUEEN.getWhiteRepresentation());
        verifyPiece(Piece.createBlackQueen(), Color.BLACK.name(), Type.QUEEN.getBlackRepresentation());

        verifyPiece(Piece.createWhiteKnight(), Color.WHITE.name(), Type.KNIGHT.getWhiteRepresentation());
        verifyPiece(Piece.createBlackKnight(), Color.BLACK.name(), Type.KNIGHT.getBlackRepresentation());

        verifyPiece(Piece.createWhiteRook(), Color.WHITE.name(), Type.ROOK.getWhiteRepresentation());
        verifyPiece(Piece.createBlackRook(), Color.BLACK.name(), Type.ROOK.getBlackRepresentation());

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

    private void verifyPiece(final Piece piece, final String color, final String representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }
}
