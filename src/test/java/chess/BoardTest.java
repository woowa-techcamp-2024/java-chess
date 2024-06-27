package chess;

import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.pieces.Piece;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.stream.Stream;

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

    @Test
    @DisplayName("주어진 위치의 기물을 조회")
    void findPiece_by_pos() {
        assertEquals(Piece.createBlack(Type.ROOK), board.findPiece("a8"));
        assertEquals(Piece.createBlack(Type.ROOK), board.findPiece("h8"));
        assertEquals(Piece.createWhite(Type.ROOK), board.findPiece("a1"));
        assertEquals(Piece.createWhite(Type.ROOK), board.findPiece("h1"));
    }

}
