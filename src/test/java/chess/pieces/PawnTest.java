package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @DisplayName("흰색 폰이 한 칸 이동하는지 확인")
    @Test
    void verifyMoveCoordinate() {
        Pawn pawn = Pawn.createWhitePawn();

        assertTrue(pawn.verifyMoveCoordinate("a2", "a3"));
        assertTrue(pawn.verifyMoveCoordinate("a3", "a4"));
    }

    @DisplayName("흰색 폰이 한 칸 이동이 아닌 경우 확인")
    @Test
    void verifyMoveCoordinateFalse() {
        Pawn pawn = Pawn.createWhitePawn();

        assertFalse(pawn.verifyMoveCoordinate("a2", "a2"));
        assertFalse(pawn.verifyMoveCoordinate("a2", "a1"));
        assertFalse(pawn.verifyMoveCoordinate("a2", "b1"));
    }

    @DisplayName("검은색 폰이 한 칸 이동하는지 확인")
    @Test
    void verifyMoveCoordinateBlack() {
        Pawn pawn = Pawn.createBlackPawn();

        assertTrue(pawn.verifyMoveCoordinate("a7", "a6"));
        assertTrue(pawn.verifyMoveCoordinate("a6", "a5"));
    }

    @DisplayName("검은색 폰이 한 칸 이동이 아닌 경우 확인")
    @Test
    void verifyMoveCoordinateFalseBlack() {
        Pawn pawn = Pawn.createBlackPawn();

        assertFalse(pawn.verifyMoveCoordinate("a7", "a7"));
        assertFalse(pawn.verifyMoveCoordinate("a7", "a8"));
        assertFalse(pawn.verifyMoveCoordinate("a7", "c6"));
    }


}