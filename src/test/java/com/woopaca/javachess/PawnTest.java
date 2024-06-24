package com.woopaca.javachess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @DisplayName("색상이 있는 폰이 생성되어야 한다.")
    @Test
    void create() {
        String white = "white";
        Pawn pawn1 = new Pawn(white);
        verifyPawn(pawn1, white);

        String black = "black";
        Pawn pawn2 = new Pawn(black);
        verifyPawn(pawn2, black);
    }

    void verifyPawn(Pawn pawn, String color) {
        assertThat(pawn.getColor()).isEqualTo(color);
    }

}
