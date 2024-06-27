package chess.view;

import chess.board.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.utils.StringUtils.NEWLINE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChessViewTest {

    @Test
    @DisplayName("보드를 출력할 수 있다")
    void printBoard() {
        // given
        Board board = new Board();
        ChessView chessView = new ChessView();

        // when
        String printedBoard = chessView.printBoard(board);

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