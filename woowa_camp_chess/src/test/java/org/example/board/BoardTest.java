package org.example.board;


import org.example.chess.board.Board;
import org.example.chess.pieces.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import static org.example.chess.pieces.Pawn.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    @DisplayName("보드에 폰을 추가한다.")
    public void create() {
        Board board = new Board();

        Pawn white = new Pawn(WHITE_COLOR);
        verifyAddPawn(board, white, 1, 0);

        Pawn black = new Pawn(BLACK_COLOR);
        verifyAddPawn(board, black, 2, 1);
    }

    private static void verifyAddPawn(Board board, Pawn white, int expected, int i) {
        board.add(white);
        assertEquals(expected, board.size());
        assertEquals(white, board.findPawn(i));
    }
}
