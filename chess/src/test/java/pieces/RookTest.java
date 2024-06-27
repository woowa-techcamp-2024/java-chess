package pieces;

import chess.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookTest {
    @Test
    public void canMove(){
        // given
        Rook rook = new Rook(PieceColor.WHITE, PieceType.ROOK, new Position("a1"));
        // when
        boolean ret = rook.canMove(new Blank(PieceColor.NO_COLOR, PieceType.NO_PIECE, new Position("a3")));
        assertTrue(ret);
    }

    @Test
    public void canMoveFalse(){
        // given
        Rook rook = new Rook(PieceColor.WHITE, PieceType.ROOK, new Position("a1"));
        // when
        boolean ret = rook.canMove(new Pawn(PieceColor.WHITE, PieceType.PAWN, new Position("a2")));
        assertFalse(ret);
    }
}
