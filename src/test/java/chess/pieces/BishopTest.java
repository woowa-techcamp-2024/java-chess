package chess.pieces;

import chess.board.Board;
import chess.board.BoardFactory;
import chess.board.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class BishopTest {
    @Test
    void testBishopCanMove() {
        Board board = BoardFactory.createEmpty();
        Bishop bishop = PieceFactory.createWhiteBishop(Position.from("d4"));
        board.setPiece(bishop);

        assertTrue(bishop.canMove(board.findPiece(Position.from("a1"))));
        assertTrue(bishop.canMove(board.findPiece(Position.from("g7"))));
        assertTrue(bishop.canMove(board.findPiece(Position.from("g1"))));
        assertTrue(bishop.canMove(board.findPiece(Position.from("a7"))));
        assertTrue(bishop.canMove(board.findPiece(Position.from("b2")))); // 대각선으로 이동할 수 없는 위치

        assertFalse(bishop.canMove(board.findPiece(Position.from("e4")))); // 같은 위치
        assertFalse(bishop.canMove(board.findPiece(Position.from("d8")))); // 같은 열
        assertFalse(bishop.canMove(board.findPiece(Position.from("h1")))); // 대각선으로 이동할 수 없는 위치
    }
}