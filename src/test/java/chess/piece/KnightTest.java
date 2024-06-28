package chess.piece;

import chess.piece.rule.Direction;
import chess.piece.rule.PieceMove;
import chess.piece.rule.SpecialDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.piece.PieceTest.verifyPiece;
import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    @DisplayName("나이트 기물이 정상적으로 생성된다.")
    public void create_piece() {
        verifyPiece(Knight.create(PieceColor.WHITE), Knight.create(PieceColor.BLACK), Type.KNIGHT);
    }

    @Test
    @DisplayName("기물이 기물 이동 방향과 이동 거리를 가진다.")
    public void move_piece() {
        PieceMove whiteMove = Knight.create(PieceColor.WHITE).getMoveable();
        PieceMove blackMove = Knight.create(PieceColor.BLACK).getMoveable();

        assertAll(
                () -> assertEquals(whiteMove.directions(), blackMove.directions()),
                () -> assertEquals(whiteMove.distance(), blackMove.distance()),
                () -> assertTrue(whiteMove.directions().containsAll(SpecialDirection.getKnightDirection())),
                () -> assertFalse(whiteMove.directions().containsAll(Direction.diagonalDirection())),
                () -> assertEquals(whiteMove.distance(), 1)
        );
    }
}
