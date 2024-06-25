package com.woowatechcamp.chess;

import static com.woowatechcamp.utils.StringUtils.appendNewLine;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void initialize() throws Exception {
        board.initialize();
        assertEquals(32, board.pieceCount());

        String blankRank = appendNewLine("........");
        String expected = appendNewLine("♜♞♝♛♚♝♞♜") + appendNewLine("♟♟♟♟♟♟♟♟") +
                blankRank + blankRank + blankRank + blankRank +
                appendNewLine("♙♙♙♙♙♙♙♙") + appendNewLine("♖♘♗♕♔♗♘♖");
        assertThat(expected).isEqualTo(board.showBoard());
    }
}
