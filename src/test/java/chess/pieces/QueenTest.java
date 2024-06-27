package chess.pieces;

import chess.board.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @DisplayName("퀸이 상하좌우 대각선 이동하는지 확인")
    @Test
    void verifyMoveCoordinate() {
        Queen queen = Queen.createWhiteQueen();

        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("a2")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("b1")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("b2")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("h7")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("g8")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("g7")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("a7")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("b8")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("b7")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("h8")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("h8")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("a1")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("a1")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("h1")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("h1")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("h1"), Coordinate.of("a8")));
        assertTrue(queen.verifyMoveCoordinate(Coordinate.of("h1"), Coordinate.of("a8")));
    }

    @DisplayName("퀸이 상하좌우 대각선 이동이 아닌 경우 확인")
    @Test
    void verifyMoveCoordinateFalse() {
        Queen queen = Queen.createWhiteQueen();

        assertFalse(queen.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("b3")));
        assertFalse(queen.verifyMoveCoordinate(Coordinate.of("a2"), Coordinate.of("b4")));
        assertFalse(queen.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("c2")));
        assertFalse(queen.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("g6")));
        assertFalse(queen.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("f7")));
        assertFalse(queen.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("b6")));
        assertFalse(queen.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("c7")));
    }
}