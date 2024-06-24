package com.wootecam;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PawnTest {

    @ParameterizedTest
    @ValueSource(strings = {"white", "black"})
    @DisplayName("지정한 색상의 폰이 생성되어야 한다.")
    void create(String color) {
        // when
        Pawn pawn = new Pawn(color);

        // then
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}
