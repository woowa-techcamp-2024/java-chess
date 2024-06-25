package org.example.board;


import org.example.chess.board.Board;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceType;
import org.junit.jupiter.api.BeforeAll;
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

        Piece white = new Piece(Piece.WHITE_COLOR, PieceType.PAWN);
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0));

        Piece black = new Piece(Piece.BLACK_COLOR,PieceType.PAWN);
        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(1));
    }

    @Test
    public void initialize() {
        Board board = new Board();
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
        System.out.println(board.print());
    }
}
