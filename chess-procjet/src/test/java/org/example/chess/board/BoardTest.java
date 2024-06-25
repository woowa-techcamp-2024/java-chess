package org.example.chess.board;

import static org.example.utils.StringUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void creat() throws Exception {
        board.initialize();
        assertEquals(32, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard()
        );
    }
}