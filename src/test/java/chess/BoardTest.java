package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.pieces.Piece.Color;
import static chess.pieces.Piece.Type;
import static chess.utils.StringUtils.NEWLINE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    @Test
    @DisplayName("보드를 생성할 수 있다")
    void create() {
        Board board = new Board();

        assertEquals(32, board.getTotalPieceCount());
        assertEquals(givenBoardPrint(), board.showBoard());
    }

    @DisplayName("해당하는 색상과 종류의 Piece 개수를 반환한다")
    @Test
    void getPieceCount() {
        // given
        Board board = new Board();

        // when
        int count = board.getPieceCount(Color.WHITE, Type.PAWN);

        // then
        assertEquals(count, 8);
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
