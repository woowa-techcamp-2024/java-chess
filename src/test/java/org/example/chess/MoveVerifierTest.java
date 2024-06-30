package org.example.chess;

import org.example.chess.pieces.Bishop;
import org.example.chess.pieces.NoPiece;
import org.example.chess.pieces.Pawn;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.global.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveVerifierTest {
    private Board board;
    private MoveVerifier moveVerifier;

    @Nested
    class 빈_보드가_주어졌을때 {
        @Test
        public void 출발지_자리에_말이_없으면_실패한다() throws Exception {
            board.initializeEmpty();

            Position sourcePosition = Position.of("b2");
            Position targetPosition = Position.of("b3");

            assertEquals(false ,moveVerifier.isMovable(sourcePosition, targetPosition));
        }

        @Test
        public void 목적지_자리에_이미_같은팀_말이_존재하면_실패한다() throws Exception {
            board.initializeEmpty();

            Position sourcePosition = Position.of("b2");
            Position targetPosition = Position.of("c2");
            board.setPiece(sourcePosition, Pawn.of(Piece.Color.WHITE));
            board.setPiece(targetPosition, Pawn.of(Piece.Color.WHITE));

            assertThrows(RuntimeException.class, () -> board.moveTo(sourcePosition, targetPosition));
        }
    }

    @Nested
    class Pawn이_주어졌을_때 {
        @Test
        public void pawn은_위로_한칸_움직일수있다() {
            Position sourcePosition = Position.of("b2");
            board.setPiece(sourcePosition, Pawn.of(Piece.Color.WHITE));

            Position targetPosition = Position.of("b3");
            board.moveTo(sourcePosition, targetPosition);

            board.showBoard();
            assertEquals(NoPiece.of(), board.findPiece(sourcePosition));
            assertEquals(Pawn.of(Piece.Color.WHITE), board.findPiece(targetPosition));
        }

        @Test
        public void pawn은_첫_이동에_대해서는_위로_두칸_움직일수있다() {
            Position sourcePosition = Position.of("b2");
            board.setPiece(sourcePosition, Pawn.of(Piece.Color.WHITE));

            Position targetPosition = Position.of("b4");
            board.moveTo(sourcePosition, targetPosition);

            board.showBoard();
            assertEquals(NoPiece.of(), board.findPiece(sourcePosition));
            assertEquals(Pawn.of(Piece.Color.WHITE), board.findPiece(targetPosition));
        }
    }

    @Nested
    class bishop이_주어졌을_때 {
        @Test
        public void bishop은_북동_북서_남동_남서로_이동할수있다() {
            Piece.Color white = Piece.Color.WHITE;
            Position sourcePos = Position.of(2, 3);
            Position movePosSW = Position.of(2 + 2, 3 - 2);
            Position movePosSE = Position.of(2 + 2, 3 + 2);
            Position movePosNW = Position.of(2 - 2, 3 - 2);
            Position movePosNE = Position.of(2 - 2, 3 + 2);

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

    }

    @BeforeEach()
    void setup() {
        board = new Board();
        moveVerifier = new MoveVerifier(board);
        board.initializeEmpty();
    }
}