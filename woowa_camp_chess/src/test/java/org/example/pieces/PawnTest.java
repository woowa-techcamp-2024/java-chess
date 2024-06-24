package org.example.pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void create() {
        final String BLACK = "black";
        final String WHITE = "white";
        verifyPawn(BLACK);
        verifyPawn(WHITE);
    }

    private static void verifyPawn(String color) {
        Pawn pawn1 = new Pawn(color);
        assertThat(pawn1.getColor()).isEqualTo(color);
    }
}