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
        Pawn pawn = createPawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        assertEquals(1, board.size());
        assertEquals(pawn, board.findPawn(0));

        pawn = createPawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
        assertEquals(2, board.size());
        assertEquals(pawn, board.findPawn(1));
    }

    private Pawn createPawn(final String color, final char representation) {
        Pawn pawn = new Pawn(color, representation);
        board.add(pawn);
        return pawn;
    }

    @Test
    @DisplayName("체스판을 초기화할 수 있다")
    public void initialize() {
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }

    @Test
    @DisplayName("체스판을 출력할 수 있다")
    public void print() {
        board.initialize();
        System.out.println(board.print());
    }
}
