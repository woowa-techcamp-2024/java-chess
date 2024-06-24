package com.woopaca.javachess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @DisplayName("흰색 폰이 생성되어야 한다.")
    @Test
    void create() {
        Pawn pawn = new Pawn("white");
        assertThat(pawn.getColor()).isEqualTo("white");

        Pawn blackPawn = new Pawn("black");
        assertThat(blackPawn.getColor()).isEqualTo("black");
    }

}
