package pe.goblin.chess.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.goblin.chess.pawn.Pawn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void create_empty() {
        assertEquals(0, board.size());
    }

    @Test
    void create() throws ExceedPawnException {
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
    void create_exceed_then_throw() throws ExceedPawnException {
        Pawn white1 = new Pawn(Pawn.WHITE_COLOR);
        board.add(white1);
        Pawn white2 = new Pawn(Pawn.WHITE_COLOR);
        board.add(white2);
        Pawn white3 = new Pawn(Pawn.WHITE_COLOR);
        assertThrows(Exception.class, () -> board.add(white3));
    }
}
