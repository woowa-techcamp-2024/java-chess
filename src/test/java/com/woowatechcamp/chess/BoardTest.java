package com.woowatechcamp.chess;
import static org.junit.Assert.*;

import com.woowatechcamp.chess.pieces.Color;
import com.woowatechcamp.chess.pieces.Pawn;
import org.junit.Test;

import java.util.List;

public class BoardTest {
    @Test
    public void initialize() throws Exception {
        Board board = new Board();
        board.initialize();
        assertEquals("♙♙♙♙♙♙♙♙", board.getWhitePawnsResult());
        assertEquals("♟♟♟♟♟♟♟♟", board.getBlackPawnsResult());
    }
}
