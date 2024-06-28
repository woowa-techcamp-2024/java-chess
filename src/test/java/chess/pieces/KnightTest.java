package chess.pieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {

    @Test
    public void knightCreate() {
        verifyPiece(Knight.createWhiteKnight(), Knight.createBlackKnight());
    }

    @Test
    public void verifyKnightMove() {
        Knight knight = Knight.createWhiteKnight();
        assertTrue(knight.verifyMovePosition("b1", "c3"));
        assertFalse(knight.verifyMovePosition("b1", "b4"));
    }

    private void verifyPiece(Piece whitePiece, Piece blackPiece) {
        assertTrue(whitePiece.isWhite());
        assertEquals(Piece.Type.KNIGHT, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(Piece.Type.KNIGHT, whitePiece.getType());
    }

}
