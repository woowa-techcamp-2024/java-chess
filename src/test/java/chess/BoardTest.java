package chess;

import chess.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    @DisplayName("보드엔 폰이 추가되고 폰에 대한 정보를 얻을 수 있다.")
    public void create() throws Exception {
        Board board = new Board();

        Pawn white = addPawn(board, Pawn.WHITE_COLOR);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0));

        Pawn black = addPawn(board, Pawn.BLACK_COLOR);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(1));
    }

    private Pawn addPawn(final Board board, final String color) {
        Pawn pawn = new Pawn(color);
        board.add(pawn);

        return pawn;
    }
}
