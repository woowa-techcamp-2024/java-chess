package chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.pieces.Colors;
import chess.pieces.Pawn;

import org.junit.jupiter.api.*;

public class BoardTest {
    Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("체스판에 흰색과 검은색 Pawn을 추가한다")
    public void create() {
        Pawn white = new Pawn(Colors.WHITE);
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0));

        Pawn black = new Pawn(Colors.BLACK);
        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(1));
    }
}
