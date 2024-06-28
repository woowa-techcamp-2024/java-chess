package pieces;

import chess.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KingTest {
    @Test
    public void canMove(){
        // given
        King king = new King(PieceColor.WHITE, PieceType.KING, new Position("d1"));
        // when
        boolean ret = king.canMove(new Blank(PieceColor.NO_COLOR, PieceType.NO_PIECE, new Position("d2")));
        assertTrue(ret);
    }

    @Test
    public void canMoveFalse(){
        // given
        King king = new King(PieceColor.WHITE, PieceType.KING, new Position("d1"));
        // when
        boolean ret = king.canMove(new Blank(PieceColor.NO_COLOR, PieceType.NO_PIECE, new Position("d3")));
        assertFalse(ret);
    }
}
