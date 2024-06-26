package chess.view;

import chess.board.Board;
import chess.board.Rank;
import chess.pieces.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static chess.utils.StringUtils.NEWLINE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChessViewTest {

    @DisplayName("랭크를 출력할 수 있다")
    @Test
    void printRank() {
        // given
        List<Piece> pieces = IntStream.range(0, 8)
                .mapToObj(i -> Piece.createBlank())
                .toList();
        Rank rank = Rank.initializeRank(pieces);

        // when
        String printedRank = ChessView.printRank(rank);

        // then
        assertThat(printedRank).isEqualTo("........");
    }

    @Test
    @DisplayName("보드를 출력할 수 있다")
    void printBoard() {
        // given
        Board board = new Board();

        // when
        String printedBoard = ChessView.printBoard(board);

        // then
        assertEquals(givenBoardPrint(), printedBoard);

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