package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @DisplayName("나이트가 L자로 이동하는지 확인")
    @Test
    void verifyMoveCoordinate() {
        Knight knight = Knight.createWhiteKnight();

        assertTrue(knight.verifyMoveCoordinate("a1", "b3"));
        assertTrue(knight.verifyMoveCoordinate("a1", "c2"));
        assertTrue(knight.verifyMoveCoordinate("a1", "c3"));
        assertTrue(knight.verifyMoveCoordinate("h8", "g6"));
        assertTrue(knight.verifyMoveCoordinate("h8", "f7"));
        assertTrue(knight.verifyMoveCoordinate("h8", "f6"));
        assertTrue(knight.verifyMoveCoordinate("a8", "b6"));
        assertTrue(knight.verifyMoveCoordinate("a8", "c7"));
        assertTrue(knight.verifyMoveCoordinate("a8", "c6"));
    }

    @DisplayName("나이트가 L자로 이동이 아닌 경우 확인")
    @Test
    void verifyMoveCoordinateFalse() {
        Knight knight = Knight.createWhiteKnight();

        assertFalse(knight.verifyMoveCoordinate("a1", "a2"));
        assertFalse(knight.verifyMoveCoordinate("a1", "b1"));
        assertFalse(knight.verifyMoveCoordinate("a1", "b2"));
        assertFalse(knight.verifyMoveCoordinate("h8", "h7"));
        assertFalse(knight.verifyMoveCoordinate("h8", "g8"));
        assertFalse(knight.verifyMoveCoordinate("h8", "g7"));
        assertFalse(knight.verifyMoveCoordinate("a8", "a7"));
        assertFalse(knight.verifyMoveCoordinate("a8", "b8"));
        assertFalse(knight.verifyMoveCoordinate("a8", "b7"));
    }


}