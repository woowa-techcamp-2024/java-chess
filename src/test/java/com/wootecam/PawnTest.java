package com.wootecam;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PawnTest {

    @ParameterizedTest
    @ValueSource(strings = {"white", "black"})
    void 지정한_색상의_폰을_생성할_수_있다(String color) {
        // when
        Pawn pawn = new Pawn(color);

        // then
        assertThat(pawn.getColor()).isEqualTo(color);
    }

    @Test
    void create_기본생성자() {
        // when
        Pawn pawn = new Pawn();

        // then
        assertThat(pawn.getColor()).isEqualTo("white");
    }
}
