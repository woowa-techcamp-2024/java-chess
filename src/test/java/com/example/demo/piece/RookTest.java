package com.example.demo.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @DisplayName("룩 생성시 색상을 가진다.")
    @ParameterizedTest
    @MethodSource("colors")
    public void create(final Color color) {
        Piece rook = new Rook(color);
        assertThat(rook.getColor()).isEqualTo(color);
    }

    private static Color[] colors() {
        return new Color[]{Color.WHITE, Color.BLACK};
    }

    @Test
    @DisplayName("색을 지정하지 않고 룩을 생성하면 흰색이다.")
    public void create_기본생성자() {
        Piece rook = new Rook();
        assertThat(rook.getColor()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("검은 색 룩이라면 대문자 R를 출력한다.")
    public void print_black() {
        Piece rook = new Rook(Color.BLACK);
        assertThat(rook.toString()).isEqualTo("R");
    }

    @Test
    @DisplayName("흰 색 룩이라면 소문자 r를 출력한다.")
    public void print_white() {
        Piece rook = new Rook(Color.WHITE);
        assertThat(rook.toString()).isEqualTo("r");
    }

    @Test
    @DisplayName("룩의 점수는 5점이다.")
    public void score() {
        Piece rook = new Rook();
        assertThat(rook.getPoint()).isEqualTo(5);
    }
}