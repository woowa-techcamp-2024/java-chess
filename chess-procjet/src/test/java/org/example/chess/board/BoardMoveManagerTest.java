package org.example.chess.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.Piece.Type;
import org.example.chess.pieces.PieceFactory;
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
        boardInitializeManger.initialize();

        assertEquals(PieceFactory.createBlackRook(), boardMoveManger.findPiece("a8"));
        assertEquals(PieceFactory.createBlackRook(), boardMoveManger.findPiece("h8"));
        assertEquals(PieceFactory.createWhiteRook(), boardMoveManger.findPiece("a1"));
        assertEquals(PieceFactory.createWhiteRook(), boardMoveManger.findPiece("h1"));
    }

    @Test
    void move() throws Exception {
        boardInitializeManger.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        Piece sourcePiece = boardMoveManger.findPiece("b2");
        boardMoveManger.move(sourcePosition, targetPosition);
        // 보드판 초기상탱
        assertEquals(sourcePiece.getType(), Type.PAWN);
        assertEquals(sourcePiece, boardMoveManger.findPiece("b3"));
    }
}