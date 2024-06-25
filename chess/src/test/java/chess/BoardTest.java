package chess;

import org.junit.jupiter.api.Test;
import pieces.PieceUnicode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    private final int NUM_COL = 8;
    private final String BLANK = ".";
    @Test
    public void initialize() throws Exception
    {
        Board board = new Board();
        board.initialize();
        assertEquals(PieceUnicode.WHITE_PAWN.getUnicode().repeat(NUM_COL), board.getWhitePawnsResult());
        assertEquals(PieceUnicode.BLACK_PAWN.getUnicode().repeat(NUM_COL), board.getBlackPawnsResult());

        StringBuilder sb = new StringBuilder();
        sb.append(BLANK.repeat(NUM_COL)).append("\n")
                        .append(PieceUnicode.BLACK_PAWN.getUnicode().repeat(NUM_COL)).append("\n")
                .append(BLANK.repeat(NUM_COL)).append("\n")
                .append(BLANK.repeat(NUM_COL)).append("\n")
                .append(BLANK.repeat(NUM_COL)).append("\n")
                .append(BLANK.repeat(NUM_COL)).append("\n")
                .append(PieceUnicode.WHITE_PAWN.getUnicode().repeat(NUM_COL)).append("\n")
                .append(BLANK.repeat(NUM_COL)).append("\n");
        assertEquals(sb.toString(), board.show());
    }
}
