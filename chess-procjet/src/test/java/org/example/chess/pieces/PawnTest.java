package org.example.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("폰 생성시 색을 지정해서 생성한다.")
    public void create() {
        verifyPawn(Color.WHITE, Pawn.WHITE_REPRESENTATION);
        verifyPawn(Color.BLACK, Pawn.BLACK_REPRESENTATION);
    }

    private void verifyPawn(final Color color, final char representation) {
        Pawn pawn = new Pawn(color, representation);
        assertEquals(color, pawn.getColor());
        assertEquals(representation, pawn.getRepresentation());
    }

    @Test
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals(Color.WHITE, pawn.getColor());
        assertEquals(Pawn.WHITE_REPRESENTATION, pawn.getRepresentation());
    }

}