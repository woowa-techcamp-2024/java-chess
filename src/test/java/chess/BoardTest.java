package chess;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;

import chess.pieces.Representations;
import org.junit.jupiter.api.*;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("체스판을 출력한다")
    void print() {
        board.initialize();

        String print = board.print();
        assertThat(print).hasSize(72);
        System.out.println(print);
    }

    @Test
    @DisplayName("전체 체스판의 상태를 확인한다")
    public void create() throws Exception {
        board.initialize();
        String blankRank = appendNewLine("........");

        assertEquals(32, board.pieceCount());
        assertEquals(
                appendNewLine("♜♞♝♛♚♝♞♜") +
                appendNewLine("♟♟♟♟♟♟♟♟") +
                blankRank + blankRank + blankRank + blankRank +
                appendNewLine("♙♙♙♙♙♙♙♙") +
                appendNewLine("♖♘♗♕♔♗♘♖"),
                board.print());
    }

    @Test
    @DisplayName("체스판 초기화 후 각 기물의 갯수를 확인한다")
    public void countPiece() {
        board.initialize();

        assertThat(board.pieceCount(Representations.BLACK_ROOK)).isEqualTo(2);
        assertThat(board.pieceCount(Representations.BLACK_KNIGHT)).isEqualTo(2);
        assertThat(board.pieceCount(Representations.BLACK_BISHOP)).isEqualTo(2);
        assertThat(board.pieceCount(Representations.BLACK_QUEEN)).isEqualTo(1);
        assertThat(board.pieceCount(Representations.BLACK_KING)).isEqualTo(1);
        assertThat(board.pieceCount(Representations.BLACK_PAWN)).isEqualTo(8);

        assertThat(board.pieceCount(Representations.WHITE_ROOK)).isEqualTo(2);
        assertThat(board.pieceCount(Representations.WHITE_KNIGHT)).isEqualTo(2);
        assertThat(board.pieceCount(Representations.WHITE_BISHOP)).isEqualTo(2);
        assertThat(board.pieceCount(Representations.WHITE_QUEEN)).isEqualTo(1);
        assertThat(board.pieceCount(Representations.WHITE_KING)).isEqualTo(1);
        assertThat(board.pieceCount(Representations.WHITE_PAWN)).isEqualTo(8);
    }
}
