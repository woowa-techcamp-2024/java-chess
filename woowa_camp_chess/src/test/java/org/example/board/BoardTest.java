package org.example.board;


import org.example.chess.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.utils.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    public void create() {
        board.initializeV2();
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

    @Test
    public void initialize() {
        board.initializeV2();
        assertEquals("PPPPPPPPNNBBRRQK", board.getBlackPawnsResult());
        assertEquals("ppppppppnnbbrrqk", board.getWhitePawnsResult());
        System.out.println(board.showBoard());
    }
}
