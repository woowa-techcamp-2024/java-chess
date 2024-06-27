package chess.pieces;

import chess.board.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @DisplayName("흰색 폰이 한 칸 이동하는지 확인")
    @Test
    void verifyMoveCoordinate() {
        Pawn pawn = Pawn.createWhitePawn();

        assertTrue(pawn.verifyMoveCoordinate(Coordinate.of("a2"), Coordinate.of("a3")));
        assertTrue(pawn.verifyMoveCoordinate(Coordinate.of("a3"), Coordinate.of("a4")));
    }

    @DisplayName("흰색 폰이 한 칸 이동이 아닌 경우 확인")
    @Test
    void verifyMoveCoordinateFalse() {
        Pawn pawn = Pawn.createWhitePawn();

        assertFalse(pawn.verifyMoveCoordinate(Coordinate.of("a2"), Coordinate.of("a2")));
        assertFalse(pawn.verifyMoveCoordinate(Coordinate.of("a2"), Coordinate.of("a1")));
        assertFalse(pawn.verifyMoveCoordinate(Coordinate.of("a2"), Coordinate.of("b1")));
    }

    @DisplayName("검은색 폰이 한 칸 이동하는지 확인")
    @Test
    void verifyMoveCoordinateBlack() {
        Pawn pawn = Pawn.createBlackPawn();

        assertTrue(pawn.verifyMoveCoordinate(Coordinate.of("a7"), Coordinate.of("a6")));
        assertTrue(pawn.verifyMoveCoordinate(Coordinate.of("a6"), Coordinate.of("a5")));
    }

    @DisplayName("검은색 폰이 한 칸 이동이 아닌 경우 확인")
    @Test
    void verifyMoveCoordinateFalseBlack() {
        Pawn pawn = Pawn.createBlackPawn();

        assertFalse(pawn.verifyMoveCoordinate(Coordinate.of("a7"), Coordinate.of("a7")));
        assertFalse(pawn.verifyMoveCoordinate(Coordinate.of("a7"), Coordinate.of("a8")));
        assertFalse(pawn.verifyMoveCoordinate(Coordinate.of("a7"), Coordinate.of("c6")));
    }


}