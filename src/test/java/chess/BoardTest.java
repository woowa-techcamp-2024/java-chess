package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.pieces.Pawn;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void print() {
        board.initialize();


        assertEquals(initializedBoard, board.print());
        System.out.println(board.print());
    }
    @Test
    void create() {
        Pawn white = new Pawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0));

        Pawn black = new Pawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(1));
    }

    @Test
    @DisplayName("체스 판에 Pawn 이외의 객체가 추가되지 않도록 한다.")
    void add_not_Pawn() {
        assertThatThrownBy(() -> {
            Object o = new Object();
            board.add((Pawn) o);
        }).isInstanceOf(RuntimeException.class);
    }
}
