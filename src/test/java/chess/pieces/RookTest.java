package chess.pieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RookTest {

    @Test
    public void rookCreate() {
        verifyPiece(Rook.createWhiteRook(), Rook.createBlackRook());
    }

    @Test
    public void verifyRookMove() {
        Rook rook = Rook.createWhiteRook();
        assertTrue(rook.verifyMovePosition("a1", "a4"));
        assertTrue(rook.verifyMovePosition("a1", "h1"));

        assertFalse(rook.verifyMovePosition("a1", "b4"));
        assertFalse(rook.verifyMovePosition("a1", "b2"));
    }

    private void verifyPiece(Piece whitePiece, Piece blackPiece) {
        assertTrue(whitePiece.isWhite());
        assertEquals(Piece.Type.ROOK, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(Piece.Type.ROOK, whitePiece.getType());
    }
}
