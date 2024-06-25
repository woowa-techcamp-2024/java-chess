package org.example.chess;

import org.example.chess.pieces.Pawn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private static Pawn black;
    private static Pawn white;

    @Test
    public void create() throws Exception {
        Board board = new Board();

        assertEquals(board.size(), 0);
    }

    @Test
    public void board_add() {
        Board board = new Board();
        board.add(white);
        board.add(black);

        assertEquals(board.size(), 2);
    }

    @Test
    public void board_findPawn() {
        Board board = new Board();
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0));

        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(1));
    }

    @Test
    public void initialize() throws Exception {
        Board board = new Board();
        board.initialize();

        assertEquals("pppppppp", board.getWhitePawnsRepresentation());
        assertEquals("PPPPPPPP", board.getBlackPawnsRespresentation());
    }

    @BeforeAll
    public static void setup(){
        white = new Pawn(Pawn.Color.WHITE);
        black = new Pawn(Pawn.Color.BLACK);
    }
}