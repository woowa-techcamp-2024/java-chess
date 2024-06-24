package org.example.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.pieces.Pawn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;
    private static Pawn white;
    private static Pawn black;

    @BeforeAll
    public static void setUpPawn() {
        white = new Pawn(Pawn.WHITE_COLOR);
        black = new Pawn(Pawn.BLACK_COLOR);
    }

    @BeforeEach
    public void setUpBoard() {
        board = new Board();
    }

    @Test
    @DisplayName("보드에 폰을 추가할 수 있다")
    public void create() throws Exception {
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0));

        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(1));
    }

    @Test
    @DisplayName("잘못된 index로 폰을 찾을 경우 에러를 발생시킨다")
    public void findPawn() throws Exception {
        Board board = new Board();
        Pawn white = new Pawn(Pawn.WHITE_COLOR);
        board.add(white);
        assertEquals(white, board.findPawn(0));
        try {
            board.findPawn(1);
        } catch (Exception e) {
            assertEquals("IndexOutOfBoundsException", e.getClass().getSimpleName());
        }
    }
}
