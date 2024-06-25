package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.pieces.Pawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    public Board board;
    public Pawn white;
    public Pawn black;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initialize();
        white = new Pawn(Pawn.WHITE_COLOR);
        black = new Pawn(Pawn.BLACK_COLOR);
    }

    @Test
    @DisplayName("보드에 폰을 추가할 수 있다")
    void create() {
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0));

        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(1));
    }

    @Test
    @DisplayName("보드를 초기화할 수 있다")
    void initialize() {
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }

    @Test
    @DisplayName("보드를 출력할 수 있다")
    void print()  {
        // given
        String givenBoard = givenBoardPrint();

        // when
        String boardPrint = board.print();

        // then
        assertEquals(givenBoard, boardPrint);
    }
    private String givenBoardPrint() {
        StringBuilder sb = new StringBuilder();
            sb.append("........").append("\n");
            sb.append("pppppppp").append("\n");
            sb.append("........").append("\n");
            sb.append("........").append("\n");
            sb.append("........").append("\n");
            sb.append("........").append("\n");
            sb.append("PPPPPPPP").append("\n");
            sb.append("........").append("\n");
        return sb.toString();
    }
}
