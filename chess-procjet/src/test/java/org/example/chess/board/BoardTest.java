package org.example.chess.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.chess.pieces.Color;
import org.example.chess.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void create() throws Exception {
        addPawnToBoardAndVerify(new Pawn(Color.WHITE), 0);
        addPawnToBoardAndVerify(new Pawn(Color.BLACK), 1);
    }

    private void addPawnToBoardAndVerify(Pawn pawn, int expectedIdx) {
        board.add(pawn);
        assertEquals(expectedIdx + 1, board.size());
        assertEquals(pawn, board.findPawn(expectedIdx));
    }
}