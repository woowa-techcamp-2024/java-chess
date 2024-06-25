package org.example.chess.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.chess.pieces.Color;
import org.example.chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void create() throws Exception {
        addPawnToBoardAndVerify(new Piece(Color.WHITE, Piece.WHITE_REPRESENTATION), 0);
        addPawnToBoardAndVerify(new Piece(Color.BLACK, Piece.BLACK_REPRESENTATION), 1);
    }

    private void addPawnToBoardAndVerify(Piece piece, int expectedIdx) {
        board.add(piece);
        assertEquals(expectedIdx + 1, board.size());
        assertEquals(piece, board.findPawn(expectedIdx));
    }

    @Test
    @DisplayName("폰을 생성해 체스판을 초기화한다.")
    void initialize() throws Exception {
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
        board.print();
    }
}