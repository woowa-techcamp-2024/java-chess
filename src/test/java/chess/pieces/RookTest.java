package chess.pieces;

import chess.board.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @DisplayName("룩이 상하좌우 이동하는지 확인")
    @Test
    void verifyMoveCoordinate() {
        Rook rook = Rook.createWhiteRook();

        assertTrue(rook.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("a2")));
        assertTrue(rook.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("b1")));
        assertTrue(rook.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("h7")));
        assertTrue(rook.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("g8")));
        assertTrue(rook.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("a7")));
        assertTrue(rook.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("b8")));
    }

    @DisplayName("룩이 상하좌우 이동이 아닌 경우 확인")
    @Test
    void verifyMoveCoordinateFalse() {
        Rook rook = Rook.createWhiteRook();

        assertFalse(rook.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("b3")));
        assertFalse(rook.verifyMoveCoordinate(Coordinate.of("a2"), Coordinate.of("b4")));
        assertFalse(rook.verifyMoveCoordinate(Coordinate.of("a1"), Coordinate.of("c2")));
        assertFalse(rook.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("g6")));
        assertFalse(rook.verifyMoveCoordinate(Coordinate.of("h8"), Coordinate.of("f7")));
        assertFalse(rook.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("b6")));
        assertFalse(rook.verifyMoveCoordinate(Coordinate.of("a8"), Coordinate.of("c7")));
    }


}