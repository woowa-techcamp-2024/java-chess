package org.example.chess;

import org.example.chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.utils.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private static Piece black;
    private static Piece white;
    private Board board;

    @Test
    public void board_add() {
        Board board = new Board();
        board.add(white);
        board.add(black);

        assertEquals(board.pieceCount(), 2);
    }

    @Test
    public void board_findPawn() {
        Board board = new Board();
        board.add(white);
        assertEquals(1, board.pieceCount());
        assertEquals(white, board.findPawn(0));

        board.add(black);
        assertEquals(2, board.pieceCount());
        assertEquals(black, board.findPawn(1));
    }

    @Test
    public void initialize() throws Exception {
        Board board = new Board();
        board.initialize();

        assertEquals("pppppppp", board.getWhitePawnsRepresentation());
        assertEquals("PPPPPPPP", board.getBlackPawnsRespresentation());
    }

    @Test
    public void create() throws Exception {
        board.initialize();
        assertEquals(32, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard());
    }

    @BeforeEach
    public void set() {
        white = Piece.createWhitePawn();
        black = Piece.createWhitePawn();
        board = new Board();
    }
}