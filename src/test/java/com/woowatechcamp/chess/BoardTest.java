package com.woowatechcamp.chess;
import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {
    @Test
    public void initialize() throws Exception {
        Board board = new Board();
        board.initialize();
        assertEquals("♙♙♙♙♙♙♙♙", board.getWhitePawnsResult());
        assertEquals("♟♟♟♟♟♟♟♟", board.getBlackPawnsResult());
    }
}
