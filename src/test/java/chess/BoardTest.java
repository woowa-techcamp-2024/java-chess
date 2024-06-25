package chess;

import chess.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private Board board;
    @BeforeEach
    void boardInit(){
        board = new Board();
    }
    @Test
    public void create() throws Exception {
        Pawn white = new Pawn(Pawn.WHITE_COLOR);
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0));

        Pawn black = new Pawn(Pawn.BLACK_COLOR);
        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(1));
    }

    @Test
    public void initialize(){
        board.initialize();
        assertEquals("pppppppp",board.getWhitePawnsResult());
        assertEquals("PPPPPPPP",board.getBlackPawnsResult());
    }

    @Test
    public void print(){
        board.initialize();
        assertEquals("""
                ........
                PPPPPPPP
                ........
                ........
                ........
                ........
                pppppppp
                ........
                """,board.print());
    }
}
