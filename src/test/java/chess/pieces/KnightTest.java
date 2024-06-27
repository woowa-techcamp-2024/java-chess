package chess.pieces;

import chess.board.Board;
import chess.board.BoardMaker;
import chess.board.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class KnightTest {

    @Test
    void testKnightCanMove() {
        Board board = new Board(BoardMaker.empty());
        Knight knight = PieceFactory.createWhiteKnight(Position.from("e4"));
        board.setPiece(knight);

        assertTrue(knight.canMove(board.findPiece(Position.from("d6"))));
        assertTrue(knight.canMove(board.findPiece(Position.from("f6"))));
        assertTrue(knight.canMove(board.findPiece(Position.from("g5"))));
        assertTrue(knight.canMove(board.findPiece(Position.from("g3"))));
        assertTrue(knight.canMove(board.findPiece(Position.from("f2"))));
        assertTrue(knight.canMove(board.findPiece(Position.from("d2"))));
        assertTrue(knight.canMove(board.findPiece(Position.from("c3"))));
        assertTrue(knight.canMove(board.findPiece(Position.from("c5"))));
    }
}