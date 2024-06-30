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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MoveVerifierTest {
    private Board board;
    private MoveVerifier moveVerifier;
    private Piece.Color white;

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

            assertThrows(RuntimeException.class, () -> moveVerifier.isMovable(sourcePosition, targetPosition));
        }

        @Test
        public void 지나가는_경로에_장애물이_존재하면_실패한다() {
            board.initializeEmpty();

            Position sourcePosition = Position.of("b2");
            Position targetPosition = Position.of("d4");
            Position obstaclePosition = Position.of("c3");
            board.setPiece(sourcePosition, Bishop.of(white));
            board.setPiece(obstaclePosition, Pawn.of(white));

            assertEquals(false, moveVerifier.isMovable(sourcePosition, targetPosition));
        }
    }

    @Nested
    class Pawn이_주어졌을_때 {
        @Test
        public void pawn은_위로_한칸_움직일수있다() {
            Position sourcePosition = Position.of("b2");
            board.setPiece(sourcePosition, Pawn.of(white));

            Position targetPosition = Position.of("b3");

            Assertions.assertEquals(true, moveVerifier.isMovable(sourcePosition, targetPosition));
        }

        @Test
        public void pawn은_첫_이동에_대해서는_위로_두칸_움직일수있다() {
            Position sourcePosition = Position.of("b2");
            board.setPiece(sourcePosition, Pawn.of(white));

            Position targetPosition = Position.of("b4");

            Assertions.assertEquals(true, moveVerifier.isMovable(sourcePosition, targetPosition));
        }
    }

    @Nested
    class bishop이_주어졌을_때 {
        @Test
        public void bishop은_북동로_이동할수있다() {
            Position sourcePos = Position.of(2, 3);
            Position movePosNE = Position.of(2 - 2, 3 + 2);

            board.setPiece(sourcePos, Bishop.of(white));

            moveVerifier.isMovable(sourcePos, movePosNE);
        }

        @Test
        public void bishop은_북서로_이동할수있다() {
            Position sourcePos = Position.of(2, 3);
            Position movePosNW = Position.of(2 - 2, 3 - 2);

            board.setPiece(sourcePos, Bishop.of(white));

            moveVerifier.isMovable(sourcePos, movePosNW);
        }
        @Test
        public void bishop은_남동로_이동할수있다() {
            Position sourcePos = Position.of(2, 3);
            Position movePosSE = Position.of(2 + 2, 3 + 2);

            board.setPiece(sourcePos, Bishop.of(white));

            moveVerifier.isMovable(sourcePos, movePosSE);
        }
        @Test
        public void bishop은_남서로_이동할수있다() {
            Position sourcePos = Position.of(2, 3);
            Position movePosSW = Position.of(2 + 2, 3 - 2);

            board.setPiece(sourcePos, Bishop.of(white));
            moveVerifier.isMovable(sourcePos, movePosSW);
        }

        @ParameterizedTest
        @CsvSource({
                "3, 3, 4, 5",
                "2, 7, 3, 3"
                })
        public void 이외의_이동은_불가하다(int r1, int c1, int r2, int c2) {
            Position sourcePos = Position.of(r1, c1);
            Position movePos = Position.of(r2, c2);

            Assertions.assertEquals(false, moveVerifier.isMovable(sourcePos, movePos));
        }
    }

    @BeforeEach()
    void setup() {
        board = new Board();
        moveVerifier = new MoveVerifier(board);
        white = Piece.Color.WHITE;
        board.initializeEmpty();
    }
}