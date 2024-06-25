package chess;

import chess.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("보드에 폰을 추가할 수 있다")
    public void create() {
        Pawn pawn = createPawn(Pawn.WHITE_COLOR);
        assertEquals(1, board.size());
        assertEquals(pawn, board.findPawn(0));

        pawn = createPawn(Pawn.BLACK_COLOR);
        assertEquals(2, board.size());
        assertEquals(pawn, board.findPawn(1));
    }

    private Pawn createPawn(final String color) {
        Pawn pawn = new Pawn(color);
        board.add(pawn);
        return pawn;
    }
}
