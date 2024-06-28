package chess;

import chess.pieces.Piece;
import chess.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static chess.pieces.Piece.Color.BLACK;
import static chess.pieces.Piece.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    Board board;
    String initializedBoard;
    final int rowSize = 8;
    final int colSize = 8;
    @BeforeEach()
    public void setUp() {
        board = new Board(rowSize, colSize);
    }


    @Test
    @DisplayName("row, col 체크")
    void checkSize() {
        assertAll(
                () -> assertEquals(board.getColSize(), colSize),
                () -> assertEquals(board.getRowSize(), rowSize)
        );
    }
}
