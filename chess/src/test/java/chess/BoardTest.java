package chess;

import org.junit.jupiter.api.Test;
import pieces.Pawn;
import pieces.PawnColor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    @Test
    public void create() throws Exception
    {
        Board board = new Board();

        Pawn whitePawn = new Pawn(PawnColor.WHITE);
        board.add(whitePawn);
        assertEquals(1, board.getSize());
        assertEquals(whitePawn, board.findPawn(0));

        Pawn blackPawn = new Pawn(PawnColor.BLACK);
        board.add(blackPawn);
        assertEquals(2, board.getSize());
        assertEquals(blackPawn, board.findPawn(1));
    }
}
