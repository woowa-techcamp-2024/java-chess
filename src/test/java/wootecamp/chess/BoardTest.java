package wootecamp.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static wootecamp.chess.util.StringUtils.appendNewline;

public class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
    }

    @Test
    @DisplayName("체스판을 초기화한다.")
    void initialize() {
        board.initialize();
        assertThat(board.pieceCount()).isEqualTo(32);

        String blankRank = appendNewline("........");
        assertThat(board.showBoard()).isEqualTo(
                appendNewline("RNBQKBNR") +
                        appendNewline("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewline("pppppppp") +
                        appendNewline("rnbqkbnr")
                );
    }
}
