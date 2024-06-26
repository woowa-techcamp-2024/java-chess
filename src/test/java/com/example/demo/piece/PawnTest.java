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
        Pawn pawn = new Pawn(color, null, null);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

    private static Color[] colors() {
        return new Color[] {Color.WHITE, Color.BLACK};
    }

    @Test
    @DisplayName("색을 지정하지 않고 폰을 생성하면 흰색이다.")
    public void create_기본생성자() {
        Pawn pawn = new Pawn(null, null);
        assertThat(pawn.getColor()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("검은 색 폰이라면 대문자 p를 출력한다.")
    public void print_black_pawn(){
        Pawn pawn = new Pawn(Color.BLACK, null, null);
        assertThat(pawn.toString()).isEqualTo("P");
    }

    @Test
    @DisplayName("흰 색 폰이라면 소문자 p를 출력한다.")
    public void print_white_pawn(){
        Pawn pawn = new Pawn(Color.WHITE, null, null);
        assertThat(pawn.toString()).isEqualTo("p");
    }

    @Test
    @DisplayName("폰의 점수는 0.5점이다.")
    public void score(){
        Pawn pawn = new Pawn(null, null);
        assertThat(pawn.getPoint()).isEqualTo(0.5f);
    }
}