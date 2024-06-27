package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @DisplayName("퀸이 상하좌우 대각선 이동하는지 확인")
    @Test
    void verifyMoveCoordinate() {
        Queen queen = Queen.createWhiteQueen();

        assertTrue(queen.verifyMoveCoordinate("a1", "a2"));
        assertTrue(queen.verifyMoveCoordinate("a1", "b1"));
        assertTrue(queen.verifyMoveCoordinate("a1", "b2"));
        assertTrue(queen.verifyMoveCoordinate("h8", "h7"));
        assertTrue(queen.verifyMoveCoordinate("h8", "g8"));
        assertTrue(queen.verifyMoveCoordinate("h8", "g7"));
        assertTrue(queen.verifyMoveCoordinate("a8", "a7"));
        assertTrue(queen.verifyMoveCoordinate("a8", "b8"));
        assertTrue(queen.verifyMoveCoordinate("a8", "b7"));
        assertTrue(queen.verifyMoveCoordinate("a1", "h8"));
        assertTrue(queen.verifyMoveCoordinate("a1", "h8"));
        assertTrue(queen.verifyMoveCoordinate("h8", "a1"));
        assertTrue(queen.verifyMoveCoordinate("h8", "a1"));
        assertTrue(queen.verifyMoveCoordinate("a8", "h1"));
        assertTrue(queen.verifyMoveCoordinate("a8", "h1"));
        assertTrue(queen.verifyMoveCoordinate("h1", "a8"));
        assertTrue(queen.verifyMoveCoordinate("h1", "a8"));
    }

    @DisplayName("퀸이 상하좌우 대각선 이동이 아닌 경우 확인")
    @Test
    void verifyMoveCoordinateFalse() {
        Queen queen = Queen.createWhiteQueen();

        assertFalse(queen.verifyMoveCoordinate("a1", "b3"));
        assertFalse(queen.verifyMoveCoordinate("a2", "b4"));
        assertFalse(queen.verifyMoveCoordinate("a1", "c2"));
        assertFalse(queen.verifyMoveCoordinate("h8", "g6"));
        assertFalse(queen.verifyMoveCoordinate("h8", "f7"));
        assertFalse(queen.verifyMoveCoordinate("a8", "b6"));
        assertFalse(queen.verifyMoveCoordinate("a8", "c7"));
    }
}