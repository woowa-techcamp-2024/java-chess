package chess.pieces;

import chess.board.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @DisplayName("나이트가 L자로 이동하는지 확인")
    @Test
    void verifyMoveCoordinate() {
        Knight knight = Knight.createWhiteKnight();

        assertTrue(knight.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("b3")));
        assertTrue(knight.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("c2")));
        assertTrue(knight.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("g6")));
        assertTrue(knight.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("f7")));
        assertTrue(knight.verifyMoveCoordinate(Coordinate.of("g8"), Coordinate.of("f6")));
        assertTrue(knight.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("b6")));
        assertTrue(knight.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("c7")));
        assertTrue(knight.verifyMoveCoordinate(Coordinate.of("b8"), Coordinate.of("c6")));
    }

    @DisplayName("나이트가 L자로 이동이 아닌 경우 확인")
    @Test
    void verifyMoveCoordinateFalse() {
        Knight knight = Knight.createWhiteKnight();

        assertFalse(knight.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("a2")));
        assertFalse(knight.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("b1")));
        assertFalse(knight.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("b2")));
        assertFalse(knight.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("h7")));
        assertFalse(knight.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("g8")));
        assertFalse(knight.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("g7")));
        assertFalse(knight.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("a7")));
        assertFalse(knight.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("b8")));
        assertFalse(knight.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("b7")));
    }


}