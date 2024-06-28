package pieces;

import chess.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopTest {
    @Test
    public void canMove(){
        // given
        Bishop bishop = new Bishop(PieceColor.WHITE, PieceType.BISHOP, new Position("c1"));
        // when
        boolean ret = bishop.canMove(new Blank(PieceColor.NO_COLOR, PieceType.NO_PIECE, new Position("b2")));
        assertTrue(ret);
    }

    @Test
    public void canMoveFalse(){
        // given
        Bishop bishop = new Bishop(PieceColor.WHITE, PieceType.BISHOP, new Position("c1"));
        // when
        boolean ret = bishop.canMove(new Pawn(PieceColor.WHITE, PieceType.PAWN, new Position("b2")));
        assertFalse(ret);
    }
}
