package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {
    @Test
    @DisplayName("흰색/검은색 폰이 생성되어야 한다.")
    public void create() {
        String white = "white";
        Pawn whitePawn = new Pawn(white);
        verifyPawn(whitePawn, white);

        String black = "black";
        Pawn blackPawn = new Pawn(black);
        verifyPawn(blackPawn, black);
    }

    private void verifyPawn(final Pawn pawn, final String expectedColor) {
        assertThat(pawn.getColor()).isEqualTo(expectedColor);
    }
}