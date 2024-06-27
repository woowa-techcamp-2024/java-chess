package chess.board;

import chess.pieces.Piece.Type;
import chess.pieces.Piece.Color;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;

public class BoardTest {

    private Board board;
    private String blankRank = appendNewLine("........");

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("체스판을 초기화하고 처음 상태를 출력합니다.")
    public void create() throws Exception {
        board.initialize();
        assertEquals(32, board.pieceCount());

        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard());
    }

    @Test
    @DisplayName("현재 체스판에 있는 기물의 개수를 반환하는 해야합니다.")
    public void countPiece() throws Exception {
        board.initialize();
        assertEquals(board.countPiece(Color.BLACK, Type.BISHOP), 2);
    }
}
