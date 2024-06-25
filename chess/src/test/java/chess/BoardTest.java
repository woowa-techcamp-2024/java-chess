package chess;

import org.junit.jupiter.api.Test;
import pieces.Pawn;
import pieces.PawnColor;
import pieces.PieceUnicode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    private final int NUM_COL = 8;

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


    @Test
    public void initialize() throws Exception
    {
        Board board = new Board();
        board.initialize();
        assertEquals(PieceUnicode.WHITE_PAWN.getUnicode().repeat(NUM_COL), board.getWhitePawnsResult());
        assertEquals(PieceUnicode.BLACK_PAWN.getUnicode().repeat(NUM_COL), board.getBlackPawnsResult());
    }
}
