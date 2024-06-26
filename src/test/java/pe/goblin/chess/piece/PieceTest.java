package pe.goblin.chess.piece;

import org.junit.jupiter.api.Test;
import pe.goblin.chess.piece.Piece.Type;

import static org.junit.jupiter.api.Assertions.*;
import static pe.goblin.chess.piece.Piece.Type.*;

class PieceTest {
    @Test
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(), Piece.createBlackPawn(), PAWN);
        verifyPiece(Piece.createWhiteKnight(), Piece.createBlackKnight(), KNIGHT);
        verifyPiece(Piece.createWhiteRook(), Piece.createBlackRook(), ROOK);
        verifyPiece(Piece.createWhiteBishop(), Piece.createBlackBishop(), BISHOP);
        verifyPiece(Piece.createWhiteQueen(), Piece.createBlackQueen(), QUEEN);
        verifyPiece(Piece.createWhiteKing(), Piece.createBlackKing(), KING);

        Piece blank = Piece.createBlank();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
    }
}
