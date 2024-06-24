package org.example.piece;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("폰 생성시 색을 지정해서 생성한다.")
    public void create() {

        final String WHITE = "white";
        final String BLACK = "black";

        verifyPawn(WHITE);
        verifyPawn(BLACK);
    }

    private void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

}