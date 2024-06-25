package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.pieces.Piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;

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
    void getBoardPiece() {
        board.initialize();
    }

    @Test
    void create() {
        board.initialize();
        assertEquals(32, board.getPieceCount());
        System.out.println(board.showBoard());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("rnbqkbnr") +
                        appendNewLine("pppppppp") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("PPPPPPPP")+
                        appendNewLine("RNBQKBNR"),
                board.showBoard());
    }

    @Test
    @DisplayName("체스 판에 Pawn 이외의 객체가 추가되지 않도록 한다.")
    void add_not_Pawn() {
        assertThatThrownBy(() -> {
            Object o = new Object();
            board.add((Piece) o);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("기물과 색에 해당하는 기물의 개수를 반환")
    void findPiece() {
        board.initialize();

        assertEquals(8, board.findPiece(Piece.Color.BLACK, Piece.Type.PAWN));
    }
}
