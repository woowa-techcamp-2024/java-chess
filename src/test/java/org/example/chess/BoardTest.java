package org.example.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.pieces.Pawn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;
    private static final int boardSize = 8;

    @BeforeEach
    public void setUpBoard() {
        board = new Board();
        board.initialize();
    }

    @Test
    public void 폰_배열검사() throws Exception {
        assertEquals(Pawn.WHITE_REPRESENTATION.repeat(boardSize), board.getWhitePawnsResult());
        assertEquals(Pawn.BLACK_REPRESENTATION.repeat(boardSize), board.getBlackPawnsResult());
    }

    @Test
    public void 보드_출력검사() throws Exception {
        //assertEquals("pppppppp", board.getWhitePawnsResult());
    }
}
