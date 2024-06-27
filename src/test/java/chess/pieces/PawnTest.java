package chess.pieces;

import chess.board.Board;
import chess.board.BoardFactory;
import chess.board.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class PawnTest {
    @Test
    void testPawnCanMove() {
        Board board = BoardFactory.createEmpty();
        Pawn pawn = PieceFactory.createWhitePawn(Position.from("d2"));
        board.setPiece(pawn);

        // 나쁜 말
        Knight blackNight = PieceFactory.createBlackKnight(Position.from("c3"));
        board.setPiece(blackNight);

        assertTrue(pawn.canMove(board.findPiece(Position.from("d4")))); // 두 칸 앞으로 이동 (첫 번째 이동)
        assertTrue(pawn.canMove(board.findPiece(Position.from("d3")))); // 한 칸 앞으로 이동

        assertTrue(pawn.canMove(board.findPiece(Position.from("c3")))); // 대각선으로 상대 말을 잡기

        assertFalse(pawn.canMove(board.findPiece(Position.from("d2")))); // 같은 위치
        assertFalse(pawn.canMove(board.findPiece(Position.from("d5")))); // 두 칸 이상 이동
        assertFalse(pawn.canMove(board.findPiece(Position.from("c4")))); // 대각선으로 이동 (상대 말이 없음)
        assertFalse(pawn.canMove(board.findPiece(Position.from("e4")))); // 대각선으로 이동 (상대 말이 없음)
    }
}
