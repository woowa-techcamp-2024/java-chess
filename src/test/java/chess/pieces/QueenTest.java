package chess.pieces;

import chess.board.Board;
import chess.board.BoardFactory;
import chess.board.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class QueenTest {
    @Test
    void testQueenCanMove() {
        Board board = BoardFactory.createEmpty();
        Queen queen = PieceFactory.createWhiteQueen(Position.from("d4"));
        board.setPiece(queen);

        assertTrue(queen.canMove(board.findPiece(Position.from("d1")))); // 위쪽으로 이동
        assertTrue(queen.canMove(board.findPiece(Position.from("d8")))); // 아래쪽으로 이동
        assertTrue(queen.canMove(board.findPiece(Position.from("a4")))); // 왼쪽으로 이동
        assertTrue(queen.canMove(board.findPiece(Position.from("h4")))); // 오른쪽으로 이동
        assertTrue(queen.canMove(board.findPiece(Position.from("a1")))); // 대각선으로 이동
        assertTrue(queen.canMove(board.findPiece(Position.from("h8")))); // 대각선으로 이동

        assertFalse(queen.canMove(board.findPiece(Position.from("b5")))); // 애매한 대각선으로 이동
        assertFalse(queen.canMove(board.findPiece(Position.from("f7")))); // 대각선으로 이동
        assertFalse(queen.canMove(board.findPiece(Position.from("d4")))); // 같은 위치
        assertFalse(queen.canMove(board.findPiece(Position.from("b7")))); // 대각선으로 이동할 수 없는 위치
    }
}