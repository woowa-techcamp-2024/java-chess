package org.example.board;

import org.example.piece.Color;
import org.example.piece.Pawn;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    @Test
    void create() throws Exception {

        Board board = new Board();

        Pawn white = new Pawn(Color.WHITE);
        board.add(white);

        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0));

        Pawn black = new Pawn(Color.BLACK);
        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(1));
    }
}