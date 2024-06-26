package pieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {
    @Test
    public void isBlackIsWhite()
    {
        Piece blackPawn = new Piece(PieceColor.BLACK, PieceType.PAWN);
        assertEquals(true, blackPawn.isBlack());
        assertEquals(false, blackPawn.isWhite());


        Piece whitePawn = new Piece(PieceColor.WHITE, PieceType.PAWN);
        assertEquals(false, whitePawn.isBlack());
        assertEquals(true, whitePawn.isWhite());
    }
}
