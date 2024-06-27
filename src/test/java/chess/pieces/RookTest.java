package chess.pieces;

import chess.board.Board;
import chess.board.BoardFactory;
import chess.board.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class RookTest {
    @Test
    void testRookCanMove() {
        Board board = BoardFactory.createEmpty();
        Rook rook = PieceFactory.createWhiteRook(Position.from("d4"));
        board.setPiece(rook);

        assertTrue(rook.canMove(board.findPiece(Position.from("d1")))); // 위쪽으로 이동
        assertTrue(rook.canMove(board.findPiece(Position.from("d8")))); // 아래쪽으로 이동
        assertTrue(rook.canMove(board.findPiece(Position.from("a4")))); // 왼쪽으로 이동
        assertTrue(rook.canMove(board.findPiece(Position.from("h4")))); // 오른쪽으로 이동

        assertFalse(rook.canMove(board.findPiece(Position.from("d4")))); // 같은 위치
        assertFalse(rook.canMove(board.findPiece(Position.from("b7")))); // 대각선으로 이동할 수 없는 위치
        assertFalse(rook.canMove(board.findPiece(Position.from("f2")))); // 대각선으로 이동할 수 없는 위치
    }
}