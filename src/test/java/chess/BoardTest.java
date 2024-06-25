package chess;

import chess.pieces.Piece;
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
        Piece white = new Piece(Piece.WHITE_COLOR);
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0));

        Piece black = new Piece(Piece.BLACK_COLOR);
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
