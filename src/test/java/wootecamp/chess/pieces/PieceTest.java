package wootecamp.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {
    @Test
    @DisplayName("팩토리 메소드로 기물들을 올바르게 생성하는지 테스트한다.")
    public void createPieces() {
        verifyPiece(Piece.createWhitePawn(), Piece.PAWN, Piece.WHITE_COLOR, Piece.WHITE_PAWN_REPRESENTATION);
        verifyPiece(Piece.createBlackPawn(), Piece.PAWN, Piece.BLACK_COLOR, Piece.BLACK_PAWN_REPRESENTATION);

        verifyPiece(Piece.createWhiteRook(), Piece.ROOK, Piece.WHITE_COLOR, Piece.WHITE_ROOK_REPRESENTATION);
        verifyPiece(Piece.createBlackRook(), Piece.ROOK, Piece.BLACK_COLOR, Piece.BLACK_ROOK_REPRESENTATION);

        verifyPiece(Piece.createWhiteKnight(), Piece.KNIGHT, Piece.WHITE_COLOR, Piece.WHITE_KNIGHT_REPRESENTATION);
        verifyPiece(Piece.createBlackKnight(), Piece.KNIGHT, Piece.BLACK_COLOR, Piece.BLACK_KNIGHT_REPRESENTATION);

        verifyPiece(Piece.createWhiteBishop(), Piece.BISHOP, Piece.WHITE_COLOR, Piece.WHITE_BISHOP_REPRESENTATION);
        verifyPiece(Piece.createBlackBishop(), Piece.BISHOP, Piece.BLACK_COLOR, Piece.BLACK_BISHOP_REPRESENTATION);

        verifyPiece(Piece.createWhiteQueen(), Piece.QUEEN, Piece.WHITE_COLOR, Piece.WHITE_QUEEN_REPRESENTATION);
        verifyPiece(Piece.createBlackQueen(), Piece.QUEEN, Piece.BLACK_COLOR, Piece.BLACK_QUEEN_REPRESENTATION);

        verifyPiece(Piece.createWhiteKing(), Piece.KING, Piece.WHITE_COLOR, Piece.WHITE_KING_REPRESENTATION);
        verifyPiece(Piece.createBlackKing(), Piece.KING, Piece.BLACK_COLOR, Piece.BLACK_KING_REPRESENTATION);
    }

    private void verifyPiece(final Piece piece, String name, final String color, final String representation) {
        assertThat(piece.getName()).isEqualTo(name);
        assertThat(piece.getColor()).isEqualTo(color);
        assertEquals(representation, piece.getRepresentation());
    }
}
