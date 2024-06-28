package chess.pieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueenTest {

    @Test
    public void queenCreate() {
        verifyPiece(Queen.createWhiteQueen(), Queen.createBlackQueen());
    }

    @Test
    public void verifyQueenMove() {
        Queen queen = Queen.createWhiteQueen();

        assertTrue(queen.verifyMovePosition("c1", "e3"));
        assertTrue(queen.verifyMovePosition("c1", "b2"));

        assertFalse(queen.verifyMovePosition("c1", "f2"));
        assertFalse(queen.verifyMovePosition("c1", "d4"));

        Rook rook = Rook.createWhiteRook();
        assertTrue(rook.verifyMovePosition("a1", "a4"));
        assertTrue(rook.verifyMovePosition("a1", "h1"));

        assertFalse(rook.verifyMovePosition("a1", "b4"));
        assertFalse(rook.verifyMovePosition("a1", "b2"));
    }

    private void verifyPiece(Piece whitePiece, Piece blackPiece) {
        assertTrue(whitePiece.isWhite());
        assertEquals(Piece.Type.QUEEN, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(Piece.Type.QUEEN, whitePiece.getType());
    }

}
