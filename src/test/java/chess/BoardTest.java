package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.pieces.Piece;

import static chess.utils.StringUtils.NEWLINE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    public Board board;
    public Piece white;
    public Piece black;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initialize();
        white = new Piece(Piece.WHITE_COLOR);
        black = new Piece(Piece.BLACK_COLOR);
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
            sb.append("........").append(NEWLINE);
            sb.append("pppppppp").append(NEWLINE);
            sb.append("........").append(NEWLINE);
            sb.append("........").append(NEWLINE);
            sb.append("........").append(NEWLINE);
            sb.append("........").append(NEWLINE);
            sb.append("PPPPPPPP").append(NEWLINE);
            sb.append("........").append(NEWLINE);
        return sb.toString();
    }
}
