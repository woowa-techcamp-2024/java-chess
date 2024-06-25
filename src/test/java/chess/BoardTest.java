package chess;

import chess.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    public void 보드_생성() {
        Board board = new Board();

        Pawn white = new Pawn(Pawn.Color.WHITE);
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0, Pawn.Color.WHITE));

        Pawn black = new Pawn(Pawn.Color.BLACK);
        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(0, Pawn.Color.BLACK));
    }

    @Test
    public void 보드_초기화() {
        Board board = new Board();
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }
}
