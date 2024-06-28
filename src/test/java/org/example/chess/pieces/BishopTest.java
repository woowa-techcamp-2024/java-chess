package org.example.chess.pieces;

import org.example.chess.Board;
import org.example.chess.pieces.global.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BishopTest {
    private Board board;

    @Test
    public void 주어진_bishop_이동은_유효하다() {
        Piece.Color white = Piece.Color.WHITE;
        Position sourcePos = Position.of(2, 3);
        Position movePosSW = Position.of(2+2, 3-2);
        Position movePosSE = Position.of(2+2, 3+2);
        Position movePosNW = Position.of(2-2, 3-2);
        Position movePosNE = Position.of(2-2, 3+2);

        board.setPiece(sourcePos, Bishop.of(white));
        board.moveTo(sourcePos, movePosSW);
        Assertions.assertEquals(Bishop.of(white), board.findPiece(movePosSW));

        board.setPiece(sourcePos, Bishop.of(white));
        board.moveTo(sourcePos, movePosSE);
        Assertions.assertEquals(Bishop.of(white), board.findPiece(movePosSE));

        board.setPiece(sourcePos, Bishop.of(white));
        board.moveTo(sourcePos, movePosNW);
        Assertions.assertEquals(Bishop.of(white), board.findPiece(movePosNW));

        board.setPiece(sourcePos, Bishop.of(white));
        board.moveTo(sourcePos, movePosNE);
        Assertions.assertEquals(Bishop.of(white), board.findPiece(movePosNE));
    }

    @BeforeEach
    public void init() {
        board = new Board();
    }
}
