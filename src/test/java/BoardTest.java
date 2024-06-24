import chess.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.pieces.Pawn;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    Board board;
    @BeforeEach()
    public void setUp() {
        board = new Board();
    }

    @Test
    public void create() throws Exception {
        Pawn white = new Pawn(Pawn.WHITE_COLOR);
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0));

        Pawn black = new Pawn(Pawn.BLACK_COLOR);
        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(1));
    }

    @Test
    @DisplayName("체스 판에 Pawn 이외의 객체가 추가되지 않도록 한다.")
    public void add_not_Pawn() {
        assertThatThrownBy(() -> {
            Object o = new Object();
            board.add((Pawn) o);
        }).isInstanceOf(RuntimeException.class);
    }
}
