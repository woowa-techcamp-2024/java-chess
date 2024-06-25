package org.example.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
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
        assertEquals(Piece.WHITE_PAWN_REPRESENTATION.repeat(boardSize), board.getWhitePawnsResult());
        assertEquals(Piece.BLACK_PAWN_REPRESENTATION.repeat(boardSize), board.getBlackPawnsResult());
    }

    @Test
    public void 보드_출력검사() throws Exception {
        String emptyLine = ".".repeat(boardSize) + "\n";

        String expected =
            emptyLine +
                Piece.WHITE_PAWN_REPRESENTATION.repeat(boardSize) + "\n" +
                emptyLine +
                emptyLine +
                emptyLine +
                emptyLine +
                Piece.BLACK_PAWN_REPRESENTATION.repeat(boardSize) + "\n" +
                emptyLine;

        assertEquals(expected, board.print());
    }
}
