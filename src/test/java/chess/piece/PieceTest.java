package chess.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class PieceTest {

    protected static void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertSame(whitePiece.getColor(), PieceColor.WHITE);
        assertEquals(type, whitePiece.getType());

        assertSame(blackPiece.getColor(), PieceColor.BLACK);
        assertEquals(type, blackPiece.getType());
    }

}
