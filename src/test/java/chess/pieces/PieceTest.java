package chess.pieces;

import org.junit.jupiter.api.*;

import static chess.pieces.Representations.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    @Test
    @DisplayName("모든 종류의 기물이 생성되어야 한다")
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(), Piece.createBlackPawn(), Type.PAWN);
        verifyPiece(Piece.createWhiteKnight(), Piece.createBlackKnight(), Type.KNIGHT);
        verifyPiece(Piece.createWhiteRook(), Piece.createBlackRook(), Type.ROOK);
        verifyPiece(Piece.createWhiteBishop(), Piece.createBlackBishop(), Type.BISHOP);
        verifyPiece(Piece.createWhiteQueen(), Piece.createBlackQueen(), Type.QUEEN);
        verifyPiece(Piece.createWhiteKing(), Piece.createBlackKing(), Type.KING);

        Piece blank = Piece.createBlank();
        assertFalse(blank.getColor().isWhite());
        assertFalse(blank.getColor().isBlack());
        assertEquals(Type.NO_PIECE, blank.getRepresentation().getType());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.getColor().isWhite());
        assertEquals(type, whitePiece.getRepresentation().getType());

        assertTrue(blackPiece.getColor().isBlack());
        assertEquals(type, blackPiece.getRepresentation().getType());
    }
}
