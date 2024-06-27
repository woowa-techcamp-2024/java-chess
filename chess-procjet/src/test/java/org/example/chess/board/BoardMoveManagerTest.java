package org.example.chess.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.Piece.PieceFactory;
import org.example.chess.pieces.Piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardMoveManagerTest {

    private Board board;
    private BoardInitializeManger boardInitializeManger;
    private BoardMoveManger boardMoveManger;

    @BeforeEach
    void setUp() {
        board = new Board();
        boardInitializeManger = new BoardInitializeManger(board);
        boardMoveManger = new BoardMoveManger(board);
    }

    @Test
    void findPiece() throws Exception {
        boardInitializeManger.initialize(board);

        assertEquals(PieceFactory.createBlackRook(), boardMoveManger.findPiece(board, "a8"));
        assertEquals(PieceFactory.createBlackRook(), boardMoveManger.findPiece(board, "h8"));
        assertEquals(PieceFactory.createWhiteRook(), boardMoveManger.findPiece(board, "a1"));
        assertEquals(PieceFactory.createWhiteRook(), boardMoveManger.findPiece(board, "h1"));
    }

    @Test
    void move() throws Exception {
        boardInitializeManger.initialize(board);

        String sourcePosition = "b2";
        String targetPosition = "b3";
        Piece sourcePiece = boardMoveManger.findPiece(board, "b2");
        boardMoveManger.move(board, sourcePosition, targetPosition);
        // 보드판 초기상탱
        assertEquals(sourcePiece.getType(), Type.PAWN);
        assertEquals(sourcePiece, boardMoveManger.findPiece(board, "b3"));
    }
}