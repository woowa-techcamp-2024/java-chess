package pieces;

import chess.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTest {
    @Test
    public void canMove(){
        // given
        Knight knight = new Knight(PieceColor.WHITE, PieceType.KNIGHT, new Position("b1"));
        Knight knight2 = new Knight(PieceColor.WHITE, PieceType.KNIGHT, new Position("b2"));
        // when
        boolean ret = knight.canMove(new Blank(PieceColor.NO_COLOR, PieceType.NO_PIECE, new Position("c3")));
        assertTrue(ret);
    }

    @Test
    public void canMoveFalse(){
        // given
        Knight knight = new Knight(PieceColor.WHITE, PieceType.KNIGHT, new Position("b1"));
        // when
        boolean ret = knight.canMove(new Pawn(PieceColor.WHITE, PieceType.PAWN, new Position("e2")));
        assertFalse(ret);
    }
}
