package chess.pieces;

import chess.board.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @DisplayName("비숍이 대각선 이동하는지 확인")
    @Test
    void verifyMoveCoordinate() {
        Bishop bishop = Bishop.createWhiteBishop();

        assertTrue(bishop.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("h8")));
        assertTrue(bishop.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("a1")));
        assertTrue(bishop.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("h1")));
        assertTrue(bishop.verifyMoveCoordinate(Coordinate.of("h1"), Coordinate.of("a8")));
        assertTrue(bishop.verifyMoveCoordinate(Coordinate.of("d4"), Coordinate.of("g1")));
        assertTrue(bishop.verifyMoveCoordinate(Coordinate.of("g1"), Coordinate.of("d4")));
        assertTrue(bishop.verifyMoveCoordinate(Coordinate.of("d4"), Coordinate.of("a7")));
        assertTrue(bishop.verifyMoveCoordinate(Coordinate.of("a7"), Coordinate.of("d4")));

    }

    @DisplayName("비숍이 대각선 이동이 아닌 경우 확인")
    @Test
    void verifyMoveCoordinateFalse() {
        Bishop bishop = Bishop.createWhiteBishop();

        assertFalse(bishop.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("a2")));
        assertFalse(bishop.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("b1")));
    }

    @DisplayName("적절하지 않은 좌표값을 입력한 경우 예외 발생")
    @Test
    void verifyMoveCoordinateException() {
        Bishop bishop = Bishop.createWhiteBishop();

        assertThrows(IllegalArgumentException.class, () -> bishop.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("a9")));
        assertThrows(IllegalArgumentException.class, () -> bishop.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("i1")));
        assertThrows(IllegalArgumentException.class, () -> bishop.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("i9")));
    }

}