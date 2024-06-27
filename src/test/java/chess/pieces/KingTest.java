package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @DisplayName("킹이 상하좌우 대각선 이동하는지 확인")
    @Test
    void verifyMoveCoordinate() {
        King king = King.createWhiteKing();

        assertTrue(king.verifyMoveCoordinate("a1", "a2"));
        assertTrue(king.verifyMoveCoordinate("a1", "b1"));
        assertTrue(king.verifyMoveCoordinate("a1", "b2"));
        assertTrue(king.verifyMoveCoordinate("h8", "h7"));
        assertTrue(king.verifyMoveCoordinate("h8", "g8"));
        assertTrue(king.verifyMoveCoordinate("h8", "g7"));
        assertTrue(king.verifyMoveCoordinate("a8", "a7"));
        assertTrue(king.verifyMoveCoordinate("a8", "b8"));
        assertTrue(king.verifyMoveCoordinate("a8", "b7"));
    }

    @DisplayName("킹이 상하좌우 대각선 이동이 아닌 경우 확인")
    @Test
    void verifyMoveCoordinateFalse() {
        King king = King.createWhiteKing();

        assertFalse(king.verifyMoveCoordinate("a1", "a3"));
        assertFalse(king.verifyMoveCoordinate("a1", "c1"));
        assertFalse(king.verifyMoveCoordinate("a1", "c3"));
        assertFalse(king.verifyMoveCoordinate("h8", "h6"));
        assertFalse(king.verifyMoveCoordinate("h8", "g6"));
        assertFalse(king.verifyMoveCoordinate("h8", "g6"));
        assertFalse(king.verifyMoveCoordinate("a8", "a6"));
        assertFalse(king.verifyMoveCoordinate("a8", "b6"));
        assertFalse(king.verifyMoveCoordinate("a8", "b5"));
    }
}