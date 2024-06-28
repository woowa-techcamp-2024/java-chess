package chess.pieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BishopTest {

    @Test
    public void bishopCreate() {
        verifyPiece(Bishop.createWhiteBishop(), Bishop.createBlackBishop());
    }

    @Test
    public void verifyBishopMove() {
        Bishop bishop = Bishop.createWhiteBishop();
        assertTrue(bishop.verifyMovePosition("c1", "e3"));
        assertTrue(bishop.verifyMovePosition("c1", "b2"));

        assertFalse(bishop.verifyMovePosition("c1", "f2"));
        assertFalse(bishop.verifyMovePosition("c1", "d4"));
    }

    private void verifyPiece(Piece whitePiece, Piece blackPiece) {
        assertTrue(whitePiece.isWhite());
        assertEquals(Piece.Type.BISHOP, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(Piece.Type.BISHOP, whitePiece.getType());
    }

}
