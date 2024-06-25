package chess;

import chess.pieces.Pawn;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    public static Board board;

    @BeforeAll
    static void setBoard(){
        board = new Board();
    }

    @Test
    @DisplayName("보드에 폰이 추가 되어야한다.")
    public void create() throws Exception {
        verifyBoard(Pawn.BLACK_COLOR);
        verifyBoard(Pawn.WHITE_COLOR);
    }

    private void verifyBoard(final String color) {
        Pawn pawn = new Pawn(color);
        board.add(pawn);
        assertEquals(pawn, board.findPawn(board.size() - 1));
    }

}
