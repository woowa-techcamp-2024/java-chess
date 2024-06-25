package chess;

import static chess.Board.BOARD_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pieces.Color;
import pieces.Pawn;

public class BoardTest {

    @Test
    public void create() {
        verifyPawn(Color.WHITE, Pawn.WHITE_REPRESENTATION);
        verifyPawn(Color.WHITE, Pawn.WHITE_REPRESENTATION);
    }

    void verifyPawn(Color color, char representation) {
        Pawn pawn = new Pawn(color, representation);
        assertEquals(color, pawn.getColor());
        assertEquals(representation, pawn.getRepresentation());
    }

    @Test
    public void initialize() {
        Board board = new Board();
        board.initialize();
        assertEquals("p".repeat(BOARD_SIZE), board.getWhitePawnsResult());
        assertEquals("P".repeat(BOARD_SIZE), board.getBlackPawnsResult());
    }
}
