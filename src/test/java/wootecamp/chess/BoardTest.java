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

    @Test
    @DisplayName("체스판 초기 상태를 확인한다.")
    void getBoardState() {
        final String emptyRank = "........";
        final String whitePawnRank = "pppppppp";
        final String blackPawnRank = "PPPPPPPP";
        final String newLine = "\n";

        board.initialize();

        String expectedState = emptyRank + newLine +
                blackPawnRank + newLine +
                emptyRank + newLine +
                emptyRank + newLine +
                emptyRank + newLine +
                emptyRank + newLine +
                whitePawnRank + newLine +
                emptyRank;

        String initBoardState = board.getBoardState();
        System.out.println(initBoardState);
        assertThat(initBoardState).isEqualTo(expectedState);
    }
}
