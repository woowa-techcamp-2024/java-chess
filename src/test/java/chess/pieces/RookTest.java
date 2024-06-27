package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @DisplayName("룩이 상하좌우 이동하는지 확인")
    @Test
    void verifyMoveCoordinate() {
        Rook rook = Rook.createWhiteRook();

        assertTrue(rook.verifyMoveCoordinate("a1", "a2"));
        assertTrue(rook.verifyMoveCoordinate("a1", "b1"));
        assertTrue(rook.verifyMoveCoordinate("h8", "h7"));
        assertTrue(rook.verifyMoveCoordinate("h8", "g8"));
        assertTrue(rook.verifyMoveCoordinate("a8", "a7"));
        assertTrue(rook.verifyMoveCoordinate("a8", "b8"));
    }

    @DisplayName("룩이 상하좌우 이동이 아닌 경우 확인")
    @Test
    void verifyMoveCoordinateFalse() {
        Rook rook = Rook.createWhiteRook();

        assertFalse(rook.verifyMoveCoordinate("a1", "b3"));
        assertFalse(rook.verifyMoveCoordinate("a2", "b4"));
        assertFalse(rook.verifyMoveCoordinate("a1", "c2"));
        assertFalse(rook.verifyMoveCoordinate("h8", "g6"));
        assertFalse(rook.verifyMoveCoordinate("h8", "f7"));
        assertFalse(rook.verifyMoveCoordinate("a8", "b6"));
        assertFalse(rook.verifyMoveCoordinate("a8", "c7"));
    }


}