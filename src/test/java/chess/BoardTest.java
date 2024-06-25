package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("체스 판을 초기화한다.")
    void initialization() {
        var expectedBoard = """
                ♜♞♝♛♚♝♞♜
                ♟♟♟♟♟♟♟♟
                □□□□□□□□
                □□□□□□□□
                □□□□□□□□
                □□□□□□□□
                ♙♙♙♙♙♙♙♙
                ♖♘♗♕♔♗♘♖
                """;
        board.initialize();

        assertThat(board.size()).isEqualTo(32);
        assertThat(board.print()).isEqualTo(expectedBoard);
    }

}