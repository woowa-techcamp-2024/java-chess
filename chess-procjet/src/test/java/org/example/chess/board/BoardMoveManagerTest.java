package org.example.chess.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.Piece.Type;
import org.example.chess.pieces.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardMoveManagerTest {

    private Board board;
    private BoardInitializeManager boardInitializeManager;
    private BoardMoveManager boardMoveManager;

    @BeforeEach
    void setUp() {
        board = new Board();
        boardInitializeManager = new BoardInitializeManager(board);
        boardMoveManager = new BoardMoveManager(board);
        boardInitializeManager.initialize();
    }

    @Test
    void findPiece() {
        assertEquals(PieceFactory.createBlackRook(), boardMoveManager.findPiece("a8"));
        assertEquals(PieceFactory.createBlackRook(), boardMoveManager.findPiece("h8"));
        assertEquals(PieceFactory.createWhiteRook(), boardMoveManager.findPiece("a1"));
        assertEquals(PieceFactory.createWhiteRook(), boardMoveManager.findPiece("h1"));
    }

    @Test
    void movePawnForward() {
        String sourcePosition = "b2";
        String targetPosition = "b4";
        boardMoveManager.move(sourcePosition, targetPosition);

        Piece movedPiece = boardMoveManager.findPiece(targetPosition);
        assertEquals(Type.PAWN, movedPiece.getType());
    }

    @Test
    void movePawnBlockedForward() {
        String sourcePosition = "b2";
        String intermediatePosition = "b3";
        boardMoveManager.move(sourcePosition, intermediatePosition);

        String targetPosition = "b4";
        assertThrows(IllegalArgumentException.class, () -> boardMoveManager.move(sourcePosition, targetPosition));
    }

    @Test
    void movePawnTwoSteps() {
        String sourcePosition = "b2";
        String targetPosition = "b4";
        boardMoveManager.move(sourcePosition, targetPosition);

        Piece movedPiece = boardMoveManager.findPiece(targetPosition);
        assertEquals(Type.PAWN, movedPiece.getType());
    }

    @Test
    void movePawnTwoStepsWithEnemyInBetween() {
        String sourcePosition = "b2";
        String intermediatePosition = "b3";
        boardMoveManager.move(sourcePosition, intermediatePosition);

        String enemySourcePosition = "a7";
        String enemyIntermediatePosition = "a5";
        boardMoveManager.move(enemySourcePosition, enemyIntermediatePosition);

        String targetPosition = "b4";
        sourcePosition = "b2";
        String finalSourcePosition = sourcePosition;
        assertThrows(IllegalArgumentException.class, () -> boardMoveManager.move(finalSourcePosition, targetPosition));
    }

    @Test
    void movePawnDiagonalCapture() {
        String sourcePosition = "b2";
        String targetPosition = "b4";
        boardMoveManager.move(sourcePosition, targetPosition);

        sourcePosition = "c7";
        targetPosition = "c5";
        boardMoveManager.move(sourcePosition, targetPosition);

        BoardView boardView = new BoardView(board);
        sourcePosition = "b4";
        targetPosition = "c5";
        boardMoveManager.move(sourcePosition, targetPosition);
        Piece movedPiece = boardMoveManager.findPiece(targetPosition);
        assertEquals(Type.PAWN, movedPiece.getType());
    }

    @Test
    void moveKnightOverPieces() {
        String sourcePosition = "b1";
        String targetPosition = "c3";
        Piece sourcePiece = boardMoveManager.findPiece(sourcePosition);
        boardMoveManager.move(sourcePosition, targetPosition);

        assertEquals(sourcePiece.getType(), Type.KNIGHT);
        assertEquals(sourcePiece, boardMoveManager.findPiece(targetPosition));
    }
}
