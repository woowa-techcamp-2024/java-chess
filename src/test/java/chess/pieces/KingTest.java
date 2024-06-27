package chess.pieces;

import chess.board.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @DisplayName("킹이 상하좌우 대각선 이동하는지 확인")
    @Test
    void verifyMoveCoordinate() {
        King king = King.createWhiteKing();

        assertTrue(king.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("a2")));
        assertTrue(king.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("b1")));
        assertTrue(king.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("b2")));
        assertTrue(king.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("h7")));
        assertTrue(king.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("g8")));
        assertTrue(king.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("g7")));
        assertTrue(king.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("a7")));
        assertTrue(king.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("b8")));
        assertTrue(king.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("b7")));
    }

    @DisplayName("킹이 상하좌우 대각선 이동이 아닌 경우 확인")
    @Test
    void verifyMoveCoordinateFalse() {
        King king = King.createWhiteKing();

        assertFalse(king.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("a3")));
        assertFalse(king.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("c1")));
        assertFalse(king.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("c3")));
        assertFalse(king.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("h6")));
        assertFalse(king.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("g6")));
        assertFalse(king.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("g6")));
        assertFalse(king.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("a6")));
        assertFalse(king.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("b6")));
        assertFalse(king.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("b5")));
    }
}