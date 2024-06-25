package chess;

import static org.assertj.core.api.Assertions.*;
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
        assertEquals(white, board.findWhitePawn(0));

        Pawn black = new Pawn(Colors.BLACK);
        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findBlackPawn(0));
    }

    @Test
    @DisplayName("체스판을 초기화하여 기물들을 배치한다")
    public void initialize() throws Exception {
        board.initialize();
        assertEquals("♙♙♙♙♙♙♙♙", board.getWhitePawnsResult());
        assertEquals("♟♟♟♟♟♟♟♟", board.getBlackPawnsResult());
    }

    @Test
    @DisplayName("체스판을 출력한다")
    void print() {
        board.initialize();

        String print = board.print();
        assertThat(print).hasSize(72);
        System.out.println(print);
    }
}
