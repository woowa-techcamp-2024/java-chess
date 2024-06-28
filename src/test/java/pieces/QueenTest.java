package pieces;

import chess.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenTest {
    @Test
    public void canMove(){
        // given
        Queen queen = new Queen(PieceColor.WHITE, PieceType.QUEEN, new Position("e1"));
        // when
        boolean ret = queen.canMove(new Blank(PieceColor.NO_COLOR, PieceType.NO_PIECE, new Position("d2")));
        assertTrue(ret);
    }

    @Test
    public void canMoveFalse(){
        // given
        Queen queen = new Queen(PieceColor.WHITE, PieceType.QUEEN, new Position("e1"));
        // when
        boolean ret = queen.canMove(new Pawn(PieceColor.WHITE, PieceType.PAWN, new Position("e2")));
        assertFalse(ret);
    }
}
