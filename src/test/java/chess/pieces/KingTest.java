package chess.pieces;

import chess.board.Board;
import chess.board.BoardFactory;
import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

abstract class KingTest {

    @Test
    @DisplayName("King이 이동합니다")
    void testKingCanMove() {
        Board board = BoardFactory.createStandard();
        King king = PieceFactory.createWhiteKing(Position.from("e4"));
        board.setPiece(king);

        // 모든 방향으로 이동 가능
        assertTrue(king.canMove(board.findPiece(Position.from("e5"))));
        assertTrue(king.canMove(board.findPiece(Position.from("d5"))));
        assertTrue(king.canMove(board.findPiece(Position.from("f5"))));

        assertTrue(king.canMove(board.findPiece(Position.from("d4"))));
        assertTrue(king.canMove(board.findPiece(Position.from("f4"))));

        assertTrue(king.canMove(board.findPiece(Position.from("e3"))));
        assertTrue(king.canMove(board.findPiece(Position.from("d3"))));
        assertTrue(king.canMove(board.findPiece(Position.from("f3"))));

        // 같은 색
        Piece queen = PieceFactory.createWhiteQueen(Position.from("e5"));
        board.setPiece(queen);
        assertFalse(king.canMove(queen));

        // 다른 색
        Piece pawn = PieceFactory.createBlackPawn(Position.from("e5"));
        board.setPiece(pawn);
        assertTrue(king.canMove(pawn));
    }
}