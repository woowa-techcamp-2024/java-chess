package com.woowatechcamp.chess;

import static com.woowatechcamp.utils.StringUtils.appendNewLine;
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

        String blankRank = "........";
        StringBuilder result = new StringBuilder();
        result.append("♜♞♝♛♚♝♞♜");
        appendNewLine(result);
        result.append("♟♟♟♟♟♟♟♟");
        appendNewLine(result);
        result.append(blankRank);
        appendNewLine(result);
        result.append(blankRank);
        appendNewLine(result);
        result.append(blankRank);
        appendNewLine(result);
        result.append(blankRank);
        appendNewLine(result);
        result.append("♙♙♙♙♙♙♙♙");
        appendNewLine(result);
        result.append("♖♘♗♕♔♗♘♖");
        appendNewLine(result);
        assertEquals(result.toString(), board.showBoard());
    }
}
