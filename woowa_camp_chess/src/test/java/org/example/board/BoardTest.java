package org.example.board;


import org.example.chess.board.Board;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    public void create() {
        Board board = new Board();

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
