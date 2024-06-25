package wootecamp.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(board.getInitialStateWhitePawnResult()).isEqualTo("pppppppp");
        assertThat(board.getInitialStateBlackPawnResult()).isEqualTo("PPPPPPPP");
    }
}
