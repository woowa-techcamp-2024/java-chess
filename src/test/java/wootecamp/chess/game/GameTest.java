package wootecamp.chess.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import wootecamp.chess.board.Board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameTest {
    private Board board;
    private Game game;

    @BeforeEach
    void init() {
        board = new Board();
        game = new Game(board);
    }

    @Nested
    class 게임의_상태가 {
        @Test
        void READY면_start_할_수_있다() {
            assertThat(1).isZero();
        }

        @Test
        void READY면_다른_명령은_할_수_없다() {
            assertThat(1).isZero();
        }

        @Test
        void PLAYING이면_move_할_수_있다() {
            assertThat(1).isZero();
        }

        @Test
        void PLAYING이면_end_할_수_있다() {
            assertThat(1).isZero();
        }
    }
}
