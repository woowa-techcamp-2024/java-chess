package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.pieces.Piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;

class BoardTest {
    Board board;
    String initializedBoard;

    @BeforeEach()
    public void setUp() {
        board = new Board();
        initializedBoard =
                """
                   ........
                   PPPPPPPP
                   ........
                   ........
                   ........
                   ........
                   pppppppp
                   ........
                   """;
    }

    @Test
    void initialize() {
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }

    @Test
    void create() {
        board.initialize();
        assertEquals(32, board.getPieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard());
    }

    @Test
    @DisplayName("체스 판에 Pawn 이외의 객체가 추가되지 않도록 한다.")
    void add_not_Pawn() {
        assertThatThrownBy(() -> {
            Object o = new Object();
            board.add((Piece) o);
        }).isInstanceOf(RuntimeException.class);
    }
}
