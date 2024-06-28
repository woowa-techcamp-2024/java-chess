package pieces;

import chess.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {
    @Test
    public void isBlackIsWhite() throws Exception {
        Piece blackPawn = new Pawn(PieceColor.BLACK, PieceType.PAWN, new Position("a1"));
        assertEquals(true, blackPawn.isBlack());
        assertEquals(false, blackPawn.isWhite());


        Piece whitePawn = new Pawn(PieceColor.WHITE, PieceType.PAWN, new Position("a1"));
        assertEquals(false, whitePawn.isBlack());
        assertEquals(true, whitePawn.isWhite());
    }
}
