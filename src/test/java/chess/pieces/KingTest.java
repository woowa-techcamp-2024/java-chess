package chess.pieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KingTest {

    @Test
    public void kingCreate() {
        verifyPiece(King.createWhiteKing(), King.createBlackKing());
    }

    @Test
    public void verifyKingMove() {
        King king = King.createWhiteKing();
        assertTrue(king.verifyMovePosition("a1", "a2"));
        assertTrue(king.verifyMovePosition("a1", "b2"));

        assertFalse(king.verifyMovePosition("a1", "a3"));
        assertFalse(king.verifyMovePosition("a1", "c3"));
    }

    private void verifyPiece(Piece whitePiece, Piece blackPiece) {
        assertTrue(whitePiece.isWhite());
        assertEquals(Piece.Type.KING, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(Piece.Type.KING, whitePiece.getType());
    }

}
