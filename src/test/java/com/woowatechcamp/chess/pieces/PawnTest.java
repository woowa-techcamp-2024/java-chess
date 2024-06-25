package com.woowatechcamp.chess.pieces;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class PawnTest {

    @Test
    @DisplayName("흰색, 검은색 폰이 생성되어야 한다")
    public void create() {
        verifyPawn(Color.WHITE, Pawn.WHITE_REPRESENTATION);
        verifyPawn(Color.BLACK, Pawn.BLACK_REPRESENTATION);
    }

    void verifyPawn(final Color color, final char representation) {
        Pawn pawn = new Pawn(color);
        assertEquals(color, pawn.getColor());
        assertEquals(representation, pawn.getRepresentation());
    }

    @Test
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertThat(pawn.getColor()).isEqualTo(Color.WHITE);
        assertThat(pawn.getRepresentation()).isEqualTo(Pawn.WHITE_REPRESENTATION);
    }
}
