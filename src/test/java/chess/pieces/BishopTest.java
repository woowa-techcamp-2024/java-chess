package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @DisplayName("비숍이 대각선 이동하는지 확인")
    @Test
    void verifyMoveCoordinate() {
        Bishop bishop = Bishop.createWhiteBishop();

        assertTrue(bishop.verifyMoveCoordinate("a1", "h8"));
        assertTrue(bishop.verifyMoveCoordinate("a1", "h8"));
        assertTrue(bishop.verifyMoveCoordinate("h8", "a1"));
        assertTrue(bishop.verifyMoveCoordinate("h8", "a1"));
        assertTrue(bishop.verifyMoveCoordinate("a8", "h1"));
        assertTrue(bishop.verifyMoveCoordinate("a8", "h1"));
        assertTrue(bishop.verifyMoveCoordinate("h1", "a8"));
        assertTrue(bishop.verifyMoveCoordinate("h1", "a8"));
    }

    @DisplayName("비숍이 대각선 이동이 아닌 경우 확인")
    @Test
    void verifyMoveCoordinateFalse() {
        Bishop bishop = Bishop.createWhiteBishop();

        assertFalse(bishop.verifyMoveCoordinate("a1", "a2"));
        assertFalse(bishop.verifyMoveCoordinate("a1", "b1"));
    }

    @DisplayName("적절하지 않은 좌표값을 입력한 경우 예외 발생")
    @Test
    void verifyMoveCoordinateException() {
        Bishop bishop = Bishop.createWhiteBishop();

        assertThrows(IllegalArgumentException.class, () -> bishop.verifyMoveCoordinate("a1", "a9"));
        assertThrows(IllegalArgumentException.class, () -> bishop.verifyMoveCoordinate("a1", "i1"));
        assertThrows(IllegalArgumentException.class, () -> bishop.verifyMoveCoordinate("a1", "i9"));
    }

}