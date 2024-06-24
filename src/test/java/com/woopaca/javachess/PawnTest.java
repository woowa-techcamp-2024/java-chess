package com.woopaca.javachess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @DisplayName("흰색 폰이 생성되어야 한다.")
    @Test
    void create() {
        String white = "white";
        Pawn pawn = new Pawn(white);
        verifyPawn(pawn, white);

        String black = "black";
        Pawn blackPawn = new Pawn(black);
        verifyPawn(blackPawn, black);
    }

    void verifyPawn(Pawn pawn, String color) {
        assertThat(pawn.getColor()).isEqualTo(color);
    }

}
