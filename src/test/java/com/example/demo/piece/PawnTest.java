package com.example.demo.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @DisplayName("폰 생성시 색상을 가진다.")
    @ParameterizedTest
    @ValueSource(strings = {"white", "black"})
    public void create(final String color){
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}