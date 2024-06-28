package chess.piece;

import chess.piece.rule.Direction;
import chess.piece.rule.PieceMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.piece.PieceTest.verifyPiece;
import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    @DisplayName("비숍 기물이 정상적으로 생성된다.")
    public void create_piece() {
        verifyPiece(Bishop.create(PieceColor.WHITE), Bishop.create(PieceColor.BLACK), Type.BISHOP);
    }

    @Test
    @DisplayName("기물이 기물 이동 방향과 이동 거리를 가진다.")
    public void move_piece() {
        PieceMove whiteMove = Bishop.create(PieceColor.WHITE).getMoveable();
        PieceMove blackMove = Bishop.create(PieceColor.BLACK).getMoveable();

        assertAll(
                () -> assertEquals(whiteMove.directions(), blackMove.directions()),
                () -> assertEquals(whiteMove.distance(), blackMove.distance()),
                () -> assertTrue(whiteMove.directions().containsAll(Direction.diagonalDirection())),
                () -> assertFalse(whiteMove.directions().containsAll(Direction.linearDirection())),
                () -> assertEquals(whiteMove.distance(), 8)
        );
    }

}
