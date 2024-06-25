package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.StringUtils.appendNewLine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void create() {
        board.initialize();
        assertThat(board.pieceCount()).isEqualTo(32);
        String blankRank = appendNewLine(".".repeat(8));
        assertThat(
            appendNewLine("RNBQKBNR") + appendNewLine("P".repeat(8)) + blankRank + blankRank + blankRank + blankRank
                + appendNewLine("p".repeat(8)) + appendNewLine("rnbqkbnr")).isEqualTo(board.toString());
    }
}
