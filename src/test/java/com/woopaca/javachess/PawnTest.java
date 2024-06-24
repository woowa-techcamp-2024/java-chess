package com.woopaca.javachess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("폰 기물 테스트")
public class PawnTest {

    @DisplayName("색상을 갖는 폰이 생성되어야 한다.")
    @Test
    void create() {
        String white = "white";
        verifyPawn(white);
        String black = "black";
        verifyPawn(black);
    }

    void verifyPawn(String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

    @DisplayName("폰을 기본 생성하면 흰색이어야 한다.")
    @Test
    void create_default_constructor() {
        Pawn pawn = new Pawn();
        assertThat(pawn.getColor()).isEqualTo("white");
    }

}
