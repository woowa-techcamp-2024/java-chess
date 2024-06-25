package chess;

import chess.pieces.Pawn;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setBoard() {
        board = new Board();
    }

    @Test
    @DisplayName("보드판 초기화 후 흰 폰과 검은 폰이 출력되어야 한다.")
    public void initialize() throws Exception {
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }
}
