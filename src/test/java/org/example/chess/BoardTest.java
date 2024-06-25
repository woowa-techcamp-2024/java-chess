package org.example.chess;

import static org.example.utils.StringUtils.appendNewLine;
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
        assertEquals("♙♙♙♙♙♙♙♙♖♘♗♕♔♗♘♖", board.getWhitePawnsResult());
        assertEquals("♜♞♝♛♚♝♞♜♟♟♟♟♟♟♟♟", board.getBlackPawnsResult());
    }

    @Test
    public void create() throws Exception {
        assertEquals(32, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
            appendNewLine("♜♞♝♛♚♝♞♜") +
                appendNewLine("♟♟♟♟♟♟♟♟") +
                blankRank + blankRank + blankRank + blankRank +
                appendNewLine("♙♙♙♙♙♙♙♙") +
                appendNewLine("♖♘♗♕♔♗♘♖"),
            board.showBoard());
    }
}
