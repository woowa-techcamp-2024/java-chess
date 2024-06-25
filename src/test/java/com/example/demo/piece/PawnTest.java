package com.example.demo.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @DisplayName("폰 생성시 색상을 가진다.")
    @ParameterizedTest
    @MethodSource("colors")
    public void create(final Color color){
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

    private static Color[] colors() {
        return new Color[] {Color.WHITE, Color.BLACK};
    }

    @Test
    @DisplayName("색을 지정하지 않고 폰을 생성하면 흰색이다.")
    public void create_기본생성자() {
        Pawn pawn = new Pawn();
        assertThat(pawn.getColor()).isEqualTo(Color.WHITE);
    }
}