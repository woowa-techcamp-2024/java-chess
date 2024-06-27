package wootecamp.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {
    @Test
    @DisplayName("팩토리 메소드로 기물들을 올바르게 생성하는지 테스트한다.")
    public void createPieces() {
        verifyPiece(PieceFactory.createWhitePawn(), Piece.Type.PAWN, Piece.Color.WHITE);
        verifyPiece(PieceFactory.createBlackPawn(), Piece.Type.PAWN, Piece.Color.BLACK);

        verifyPiece(PieceFactory.createWhiteRook(), Piece.Type.ROOK, Piece.Color.WHITE);
        verifyPiece(PieceFactory.createBlackRook(), Piece.Type.ROOK, Piece.Color.BLACK);

        verifyPiece(PieceFactory.createWhiteKnight(), Piece.Type.KNIGHT, Piece.Color.WHITE);
        verifyPiece(PieceFactory.createBlackKnight(), Piece.Type.KNIGHT, Piece.Color.BLACK);

        verifyPiece(PieceFactory.createWhiteBishop(), Piece.Type.BISHOP, Piece.Color.WHITE);
        verifyPiece(PieceFactory.createBlackBishop(), Piece.Type.BISHOP, Piece.Color.BLACK);

        verifyPiece(PieceFactory.createWhiteQueen(), Piece.Type.QUEEN, Piece.Color.WHITE);
        verifyPiece(PieceFactory.createBlackQueen(), Piece.Type.QUEEN, Piece.Color.BLACK);

        verifyPiece(PieceFactory.createWhiteKing(), Piece.Type.KING, Piece.Color.WHITE);
        verifyPiece(PieceFactory.createBlackKing(), Piece.Type.KING, Piece.Color.BLACK);
    }

    private void verifyPiece(final Piece piece, Piece.Type type, final Piece.Color color) {
        assertThat(piece.getType()).isEqualTo(type);
        assertThat(piece.getColor()).isEqualTo(color);
    }
}
