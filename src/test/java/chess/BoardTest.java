package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.utils.StringUtils.NEWLINE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    public Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("보드를 생성할 수 있다")
    void create() {
        board.initialize();

        assertEquals(32, board.pieceCount());
        assertEquals(givenBoardPrint(), board.showBoard());
    }


    private String givenBoardPrint() {
        String blankRank = "........";
        StringBuilder sb = new StringBuilder();
            sb.append("RNBQKBNR").append(NEWLINE);
            sb.append("PPPPPPPP").append(NEWLINE);
            sb.append(blankRank).append(NEWLINE);
            sb.append(blankRank).append(NEWLINE);
            sb.append(blankRank).append(NEWLINE);
            sb.append(blankRank).append(NEWLINE);
            sb.append("pppppppp").append(NEWLINE);
            sb.append("rnbqkbnr");
        return sb.toString();
    }
}
