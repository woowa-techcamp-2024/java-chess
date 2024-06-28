package chess.pieces;

import static chess.pieces.PieceTestUtil.assertImpossibleMove;

import chess.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlankPieceTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initializeEmpty();
    }

    @DisplayName("blank piece는 움직일 수 없다.")
    @Test
    void canNotMoveBlankPiece() {
        board.initialize();
        assertImpossibleMove(board, Position.b3, Position.b4);
    }
}
