package chess.piece;

import chess.piece.rule.Direction;
import chess.piece.rule.PieceMove;
import chess.piece.rule.SpecialDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.piece.PieceTest.verifyPiece;
import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    @DisplayName("폰 기물이 정상적으로 생성된다.")
    public void create_piece() {
        verifyPiece(Pawn.create(PieceColor.WHITE), Pawn.create(PieceColor.BLACK), Type.PAWN);
    }

    @Test
    @DisplayName("기물이 기물 이동 방향과 이동 거리를 가진다.")
    public void move_piece() {
        PieceMove whiteMove = Pawn.create(PieceColor.WHITE).getMoveable();
        PieceMove blackMove = Pawn.create(PieceColor.BLACK).getMoveable();

        assertAll(
                () -> assertEquals(whiteMove.directions(), blackMove.directions()),
                () -> assertEquals(whiteMove.distance(), blackMove.distance()),
                () -> assertTrue(whiteMove.directions().containsAll(SpecialDirection.getPawnDirection())),
                () -> assertFalse(whiteMove.directions().containsAll(Direction.diagonalDirection()))
        );
    }

    @Test
    @DisplayName("폰 기물이 첫 번째 이동일 때 이동 거리가 2이다.")
    public void first_move() {
        Pawn pawn = Pawn.create(PieceColor.WHITE);

        assertEquals(pawn.getMoveable().distance(), 2);

        pawn.completeFirstMove();

        assertEquals(pawn.getMoveable().distance(), 1);
    }
}
